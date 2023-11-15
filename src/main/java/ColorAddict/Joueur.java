package ColorAddict;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

public class Joueur extends Group {

    public static final String CARD_URL = "src/assets/UnoBlankCards.png";
    public static final String BG_URL = "src/assets/Background.jpg";

    private static final int START_X_POS = 0;
    private static final int START_Y_POS = 0;
    private static final int STEP_X = 100;
    private static final int STEP_Y = 50;

    public ArrayList<Card> hand = new ArrayList<Card>();
    public ArrayList<Card> stack = new ArrayList<Card>();

    private int indexSelectedCard = 0;
    private Group[] cards = new Group[3];

    public Joueur() throws Exception {
        super();
    }


    public void addCard(Card carte){
        hand.add(carte);
    }


    public void augmenteCardIndex() {
        System.out.println("JE MONTE L4INDEX");
        if (indexSelectedCard < hand.size() - 1) {
            SelectCard(indexSelectedCard + 1);
        }
    }

    public void diminueCardIndex() {
        System.out.println("JE DIMINUE LINDEX");
        if (indexSelectedCard == 0) {
            SelectCard(cards.length-1);
        }
        SelectCard(indexSelectedCard - 1);

    }

    public void playCard(){
        stack.add(hand.get(indexSelectedCard));
        hand.remove(indexSelectedCard);
        SelectCard(0);
    }

    public void SetPioche(ArrayList<Card> pioche){
        this.stack = pioche;

    }

    public void SetMain(ArrayList<Card> main){
        this.hand = main;

        Image bg = new Image(new File(BG_URL).toURI().toString());
        ImageView bgView = new ImageView(bg);
        bgView.setFitHeight(225);
        bgView.setFitWidth(450);


        this.getChildren().add(bgView);
        int posX = START_X_POS;
        int posY = START_Y_POS;
        for (Card card : main) {
            Image image = new Image(new File(CARD_URL).toURI().toString());
            ImageView imageView = new ImageView(image);


            imageView.setFitHeight(100);
            imageView.setFitWidth(70);
            imageView.setX(posX);
            imageView.setY(posY);



            Text textUI = new Text(card.text);
            double textX = (imageView.getFitWidth() - textUI.getLayoutBounds().getWidth()) / 2;
            double textY = (imageView.getFitHeight() + textUI.getLayoutBounds().getHeight()) / 2;
            textUI.setX(textX + posX);
            textUI.setY(textY + posY);

            textUI.setFill(card.color.getColor());

            Group cardGroup = new Group();
            cardGroup.getChildren().addAll(imageView, textUI);
            cards[main.indexOf(card)] = cardGroup;

            this.getChildren().addAll(cardGroup);
            posX += STEP_X;

        }
        SelectCard(0);
        System.out.println("Main du joueur : " + hand.toString());
    }

    public void SelectCard(int index){

        if(cards[indexSelectedCard] != null){
            cards[indexSelectedCard].setTranslateY(0);
        }
        indexSelectedCard = index;
        cards[indexSelectedCard].setTranslateY(-20);
    }

    public void AddKeyListening() throws NoSuchMethodException {
        SceneManager.instance.getCurrentScene().getScene().setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.A){
                diminueCardIndex();
            }

        });

    }


}
