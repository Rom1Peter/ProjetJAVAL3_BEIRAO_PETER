package ColorAddict;

import ColorAddict.Enums.GameMode;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.PopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameManager {

    public static GameManager instance;

    public int nbJoueurs;
    public int nbIA;

    //ajout règle du jeu
    HashMap<Integer, PlayerConfig> playerConfigs = new HashMap<Integer, PlayerConfig>();


    public GameMode gameMode;

    public GameManager(){
        instance = this;

        //Creation des configs des joueurs
        playerConfigs.put(0, new PlayerConfig(KeyCode.Q, KeyCode.D, KeyCode.Z, KeyCode.S));
        playerConfigs.put(1, new PlayerConfig(KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN));
        playerConfigs.put(2, new PlayerConfig(KeyCode.C, KeyCode.B, KeyCode.F, KeyCode.V));
        playerConfigs.put(3, new PlayerConfig(KeyCode.G, KeyCode.J, KeyCode.Y, KeyCode.H));
        playerConfigs.put(4, new PlayerConfig(KeyCode.COMMA, KeyCode.COLON, KeyCode.K, KeyCode.PERIOD));
        playerConfigs.put(5, new PlayerConfig( KeyCode.L, KeyCode.STAR,KeyCode.P, KeyCode.M));

    }

    public ArrayList<Joueur> StartGame() throws Exception {
        HeapManager heapManager = new HeapManager();
        RuleManager ruleManager = new RuleManager();
        CardManager cardManager = new CardManager();
        cardManager.CreateCards();

        ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

        for (int i = 0; i < nbJoueurs; i++){
            joueurs.add(new Joueur(playerConfigs.get(i)));
        }


        joueurs = cardManager.GiveHandAndStack(joueurs);



        System.out.println("Heap card : " + heapManager.CardOnTop);

        return joueurs;
    }

    public void DisplayEndWindow(){


        SceneManager.instance.DisplayPopUp();
    }



}
