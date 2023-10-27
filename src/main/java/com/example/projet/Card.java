package com.example.projet;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;

public class Card {





    CardColor color;
    String text;

    public Card(CardColor color, String text){
        this.color = color;
        this.text = text;


    }

    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", text='" + text + '\'' +
                '}';
    }
}
