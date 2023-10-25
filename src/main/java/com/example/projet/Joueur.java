package com.example.projet;

import java.util.ArrayList;

public class Joueur {

    public ArrayList<Card> hand = new ArrayList<Card>();
    public ArrayList<Card> stack = new ArrayList<Card>();


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

        System.out.println("Main du joueur : " + hand.toString());
    }
}
