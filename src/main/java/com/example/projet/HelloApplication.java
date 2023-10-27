package com.example.projet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

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

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(joueur1, 0, 0);
        grid.add(joueur2, 0, 1);
        grid.setGridLinesVisible(true);

        Scene scene = new Scene(grid);

        //Set app size to the screen size
        stage.setX(screenSize.getMinX());
        stage.setY(screenSize.getMinY());
        stage.setWidth(screenSize.getWidth());
        stage.setHeight(screenSize.getHeight());

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
        while(true)
            Update();
    }

    public static void Update(){

    }
}