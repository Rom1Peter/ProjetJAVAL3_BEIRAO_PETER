package ColorAddict.Scenes;

import ColorAddict.CardManager;
import ColorAddict.HeapManager;
import ColorAddict.Joueur;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;

import java.util.ArrayList;



public class GameScene extends CustomScene{

    public GameScene() {

        HeapManager heapManager = new HeapManager();

        CardManager cardManager = new CardManager();
        cardManager.CreateCards();

        Joueur joueur1 = new Joueur();
        Joueur joueur2 = new Joueur();

        ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

        joueurs.add(joueur1);
        joueurs.add(joueur2);

        cardManager.GiveHandAndStack(joueurs);

        System.out.println("Heap card : " + heapManager.CardOnTop);




        // Create grid pane which will contain all players
        GridPane grid = new GridPane();
        grid.maxWidth(1450);
        grid.maxHeight(1080);
        grid.setMinSize(1450, 1080);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(joueur1, 0, 0, 1, 2);
        grid.add(joueur2, 1, 0);

        grid.setGridLinesVisible(true);

        Scene scene = new Scene(grid);
        this.scene = scene;



        //Set app size to the screen size
       /*stage.setX(screenSize.getMinX());
        stage.setY(screenSize.getMinY());
        stage.setWidth(screenSize.getWidth());
        stage.setHeight(screenSize.getHeight());

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/



    }



}
