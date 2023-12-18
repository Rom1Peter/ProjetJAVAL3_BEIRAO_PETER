package ColorAddict.Players;

import ColorAddict.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.util.ArrayList;

public abstract class PlayerAction extends Group {

    public static final String BG_URL = "src/assets/Table.jpg";
    public static boolean isGameOver = false;

    private static final int START_X_POS = 20;
    private static final int START_Y_POS = 20;
    private static final int STEP_X = 100;
    private static final int STEP_Y = 50;
    private static final double CARD_RADIUS = 150.0;
    private static final double CENTER_X = 250.0;
    private static final double CENTER_Y = 250.0;

    private static final double STACK_X = 350;
    private static final double STACK_Y = 125;

    public ArrayList<Card> hand = new ArrayList<Card>();
    public ArrayList<Card> stack = new ArrayList<Card>();
    private ArrayList<Group> cards = new ArrayList();

    private StackDisplay stackDisplay;

    private int indexSelectedCard = 0;

    private Group cardGroup;

    public PlayerAction(){
        super();

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

    public void playCard() {
        System.out.println("Je joue la carte : " + hand.get(indexSelectedCard).toString());

        if (RuleManager.instance.checkPlayCard(hand.get(indexSelectedCard))) {
            System.out.println("La carte est jouable " + hand.get(indexSelectedCard).toString() +
                    " " + (HeapManager.instance.CardOnTop != null ? HeapManager.instance.CardOnTop.toString() : "null"));

            HeapManager.instance.SetCardOnTop(hand.get(indexSelectedCard));

            hand.remove(indexSelectedCard);
            this.getChildren().remove(cards.get(indexSelectedCard));
            cards.remove(indexSelectedCard);

            if (cards.isEmpty() && stack.isEmpty()) {
                System.out.println("Le joueur a gagné");
                isGameOver = true;
                GameManager.instance.DisplayEndWindow();

                return;
            }
            indexSelectedCard = -1;

            int newIndex = hand.size()-1;
            if (newIndex >= 0 && newIndex < cards.size() && cards.get(newIndex) != null) {
                SelectCard(newIndex);
                return;
            }

            newIndex = 0;
            if (newIndex >= 0 && newIndex < cards.size() && cards.get(newIndex) != null) {
                SelectCard(newIndex);
                return;
            }
        } else {
            System.out.println("La carte n'est pas jouable " + hand.get(indexSelectedCard).toString() +
                    " " + (HeapManager.instance.CardOnTop != null ? HeapManager.instance.CardOnTop.toString() : "null"));
        }
    }


    public void SetPioche(ArrayList<Card> pioche){
        this.stack = pioche;

        //Add stack UI
        this.stackDisplay = new StackDisplay(this.stack.size(), (int) STACK_X, (int) STACK_Y);
        this.getChildren().add(this.stackDisplay.getStackUI());
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

        }
        SelectCard(0);
        RefreshCardDisplay();
        System.out.println("Main du joueur : " + hand.toString());



    }

    public void SelectCard(int index) {
        System.out.println("From " + indexSelectedCard + " to " + index);
        try {
            if (index < 0 || index >= cards.size() || cards.get(index) == null) {
                System.out.println("Invalid index or null card at index " + index);
                return;
            }

            if (indexSelectedCard != -1) {
                cards.get(indexSelectedCard).setTranslateY(0);
            }

            indexSelectedCard = index;
            cards.get(indexSelectedCard).setTranslateY(-20);
        } catch (Exception e) {
            System.out.println("Error selecting card from " + indexSelectedCard + " to " + index + " " + cards.size());
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }
    }


    public void PickCard(){
        System.out.println("Je pioche une carte");
        if(stack.isEmpty()){
            System.out.println("La pioche est vide");
            return;
        }
        if(hand.size() < 3){
            //Calculate posX and posY of new cards

            int posX = START_X_POS;
            int posY = START_Y_POS;


            //Pick card
            Card card = stack.get(0);
            System.out.println("Carte piochée :" + card);
            hand.add(card);
            stack.remove(0);

            this.stackDisplay.setStackUI(stack.size());


            //Add new card to the group
            Group cardGroup = card.getCardUI(posX, posY);
            cards.add(cardGroup);
            this.getChildren().addAll(cardGroup);

            RefreshCardDisplay();
            SelectCard(indexSelectedCard);
            System.out.println("Main du joueur : " + hand.toString());
            HeapManager.instance.CheckStuckedGame();
        }
        else{
            System.out.println("La main est pleine");
        }
    }

    public void RefreshCardDisplay(){
        //Change card position depending on their index
        //Calculate posX of cards to display
        int posX = START_X_POS;
        int posY = START_Y_POS;

        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) != null) {
                cards.get(i).setTranslateX(posX);
                cards.get(i).setTranslateY(posY);
                posX += STEP_X;
            }
        }

    }

    public int getIndexSelectedCard(){
        return indexSelectedCard;
    }




}
