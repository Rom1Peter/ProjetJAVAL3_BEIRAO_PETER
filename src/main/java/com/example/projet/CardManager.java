package com.example.projet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class CardManager {
    private final int NBCARDS_MAX = 42;
    private final int NBCARDS_MAX_JOKER = 8;
    private final int NBCARDS_MAX_PER_COLOR = 6;
    private final int NBCARDS_MAX_PER_HAND = 3;

    public ArrayList<Card> cards = new ArrayList<Card>();
    private int nbCards = 0;



    public void CreateCards(){
        int nbCardsPerColor = 0;

        //Tant que pas assez de carte dans le paquet
        while(NBCARDS_MAX != nbCards){
            System.out.println("On passe à la carte : " + nbCards);
            //Pour chaque couleur
            for(CardColor color : CardColor.values()){
                System.out.println("On passe à la couleur : " + color);

                //Tant que j'ai pas assez de carte de cette couleur
                while (NBCARDS_MAX_PER_COLOR != nbCardsPerColor){

                    //Pour chaque text possible
                    for(CardColor text : CardColor.values()){
                        System.out.println("On passe au text : " + text);
                        //On ne met pas la carte si elle a la même couleur que le text
                        if(text.name() != color.name()) {
                            cards.add(CreateCard(color, text.toString()));
                            nbCardsPerColor++;
                            nbCards++;
                        }
                    }
                }
                nbCardsPerColor = 0;

            }
        }

        //On crée les jokers
        for(int i = 0; i < NBCARDS_MAX_JOKER; i++){
            cards.add(CreateJoker());
        }
    }

    public Card CreateCard(CardColor color, String text){
        System.out.println("Just create a card with the color : " + color + "and the text : " + text);
        return new Card(color, text);
    }

    public Card CreateJoker(){
        return new Card(CardColor.BLACK, "Joker");
    }

    public void GiveHandAndStack(ArrayList<Joueur> j){

        boolean isImpair = false;
        Card cardImpair = null;

        int nbCardsPerStack;

        //On mélange les cartes
        Collections.shuffle(cards);

        Card heapCard = PickCard();

        HeapManager.instance.SetCardOnTop(heapCard);

        //On donne les cartes pour les mains aux joueurs
        for(Joueur joueur : j){
            ArrayList<Card> hand = new ArrayList<Card>();
            ArrayList<Card> stack = new ArrayList<Card>();

            for(int i = 0; i < NBCARDS_MAX_PER_HAND; i++){
                hand.add(PickCard());
            }

            joueur.SetMain(hand);
        }



        //Si impair on rend le jeu pair en prennant une carte
        if(j.size() % 2 != 0){
            isImpair = true;
            cardImpair = PickCard();

        }



        nbCardsPerStack = cards.size() / j.size();


        //On donne les cartes pour les piles aux joueurs
        for(Joueur joueur : j){
            ArrayList<Card> stack = new ArrayList<Card>();

            for(int i = 0; i < nbCardsPerStack; i++){
                stack.add(PickCard());
            }

            joueur.SetPioche(stack);
        }


    }

    public Card PickCard(){
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }
}
