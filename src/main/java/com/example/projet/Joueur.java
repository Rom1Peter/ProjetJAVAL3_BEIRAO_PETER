package com.example.projet;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

public class Joueur extends Group {

    public ArrayList<Card> hand = new ArrayList<Card>();
    public ArrayList<Card> stack = new ArrayList<Card>();


    public static final String CARD_URL = "src/assets/UnoBlankCards.png";
    public static final String BG_URL = "src/assets/Background.png";
    private static final int START_X_POS = 0;
    private static final int START_Y_POS = 0;
    private static final int STEP_X = 100;
    private static final int STEP_Y = 50;


    public void addCard(Card carte){
        hand.add(carte);
    }

    public void drawCard(){
        addCard(stack.get(0));
        stack.remove(0);
    }


    public void SetPioche(ArrayList<Card> pioche){
        this.stack = pioche;


    }

    public void SetMain(ArrayList<Card> main){
        this.hand = main;

        Image bg = new Image(new File(BG_URL).toURI().toString());
        ImageView bgView = new ImageView(bg);
        bgView.setFitHeight(300);
        bgView.setFitWidth(600);
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

        /*// Center the text within the ImageView using a StackPane
        StackPane.setAlignment(textUI, Pos.CENTER);
        StackPane.setAlignment(imageView, Pos.CENTER);*/

            this.getChildren().addAll(imageView, textUI);
            posX += STEP_X;

        }
        System.out.println("Main du joueur : " + hand.toString());
    }
}
