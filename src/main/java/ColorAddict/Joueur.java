package ColorAddict;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private static final double CARD_RADIUS = 150.0;
    private static final double CENTER_X = 250.0;
    private static final double CENTER_Y = 250.0;

    public ArrayList<Card> hand = new ArrayList<Card>();
    public ArrayList<Card> stack = new ArrayList<Card>();

    public PlayerConfig config;
    private int indexSelectedCard = 0;
    private ArrayList<Group> cards = new ArrayList();

    private Group cardGroup;

    public Joueur(PlayerConfig playerConfig) throws Exception {
        super();
        this.config = playerConfig;
    }


    public void addCard(Card carte){
        hand.add(carte);
    }


    public void augmenteCardIndex() {
        System.out.println("JE MONTE L4INDEX");
        if (indexSelectedCard + 1 > hand.size() - 1 ) {
            SelectCard(0);
            return;
        }
        SelectCard(indexSelectedCard + 1);
    }

    public void diminueCardIndex() {
        System.out.println("JE DIMINUE LINDEX");
        if (indexSelectedCard - 1 < 0) {
            SelectCard(cards.size() - 1);
            return;
        }
        SelectCard(indexSelectedCard - 1);

    }

    public void playCard(){
        System.out.println("Je joue la carte : " + hand.get(indexSelectedCard).toString());
        if(RuleManager.instance.checkPlayCard(hand.get(indexSelectedCard))){
            System.out.println("La carte est jouable " + hand.get(indexSelectedCard).toString() + " " + HeapManager.instance.CardOnTop.toString());

            HeapManager.instance.SetCardOnTop(hand.get(indexSelectedCard));

            hand.remove(indexSelectedCard);

            this.getChildren().remove(cards.get(indexSelectedCard));
            cards.remove(indexSelectedCard);

            if(cards.isEmpty()) {
                System.out.println("Le joueur a gagné");
                return;
            }
            if(cards.get(indexSelectedCard -1 ) != null){
                SelectCard(indexSelectedCard - 1);
                return;
            }
            else{
                SelectCard(indexSelectedCard + 1);
                return;
            }
        }
        else{
            System.out.println("La carte n'est pas jouable " + hand.get(indexSelectedCard).toString() + " " + HeapManager.instance.CardOnTop.toString());
        }

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


            Group cardGroup = card.getCardUI( posX, posY);
            cards.add(cardGroup);

            this.getChildren().addAll(cardGroup);
            posX += STEP_X;

        }
        SelectCard(0);
        System.out.println("Main du joueur : " + hand.toString());
    }

    public void SelectCard(int index){
        System.out.println("From " + indexSelectedCard + " to " + index);
        try {
            if(cards.get(index) == null)
                return;
            if (cards.get(indexSelectedCard) != null) {
                cards.get(indexSelectedCard).setTranslateY(0);
            }

            indexSelectedCard = index;
            cards.get(indexSelectedCard).setTranslateY(-20);
        }catch (Exception e){
            System.out.println("Erreur de selection de carte en allant de " + indexSelectedCard + " à " + index + " " + cards.size());
        }
    }

    public void PickCard(){
        if(stack.isEmpty()){
            System.out.println("La pioche est vide");
            return;
        }
        if(hand.size() < 3){
        //Place card where is empty
        Card card = stack.get(0);
        hand.add(card);
        stack.remove(0);

        //Calculate posX and posY of new cards

        int posX = START_X_POS;
        int posY = START_Y_POS;

        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).setTranslateX(posX);
            cards.get(i).setTranslateY(posY);
            posX += STEP_X;
        }

        //Add new card to the group
        Group cardGroup = card.getCardUI(posX, posY);
        cards.add(cardGroup);
        this.getChildren().addAll(cardGroup);
        SelectCard(cards.size() - 1);
        System.out.println("Main du joueur : " + hand.toString());
        }
        else{
            System.out.println("La main est pleine");
        }
    }

    public void AddKeyListening() throws NoSuchMethodException {
        SceneManager.instance.getCurrentScene().getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler(){
            @Override
            public void handle(javafx.event.Event event) {
                KeyEvent keyEvent = (KeyEvent) event;
                if(keyEvent.getCode() == config.keyLeft){
                    diminueCardIndex();
                }
                if(keyEvent.getCode() == config.keyRight){
                    augmenteCardIndex();
                }

                if(keyEvent.getCode() == config.keyUp){
                    playCard();
                }

                if(keyEvent.getCode() == config.keyDown){
                    PickCard();
                }
            }
        });


    }


}
