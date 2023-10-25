package com.example.projet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Hello!");
        stage.show();

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
    }

    public static void main(String[] args) {
        launch();
        while(true)
            Update();
    }

    public static void Update(){

    }
}