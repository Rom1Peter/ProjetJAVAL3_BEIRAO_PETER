package ColorAddict;

import ColorAddict.Enums.GameMode;

import java.util.ArrayList;

public class GameManager {

    public static GameManager instance;

    public int nbJoueurs;
    public int nbIA;

    //ajout règle du jeu

    public GameMode gameMode;

    public GameManager(){
        instance = this;
    }

    public ArrayList<Joueur> StartGame(){
        HeapManager heapManager = new HeapManager();

        CardManager cardManager = new CardManager();
        cardManager.CreateCards();

        ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

        for (int i = 0; i < nbJoueurs; i++){
            joueurs.add(new Joueur());
        }


        joueurs = cardManager.GiveHandAndStack(joueurs);

        System.out.println("Heap card : " + heapManager.CardOnTop);

        return joueurs;
    }


}
