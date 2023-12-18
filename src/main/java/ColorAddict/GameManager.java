package ColorAddict;

import ColorAddict.AI.AIPlayer;
import ColorAddict.Enums.GameMode;
import ColorAddict.Players.Player;
import ColorAddict.Players.PlayerAction;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {

    public static GameManager instance;

    public int nbJoueurs;

    public int nbIA;

    //ajout règle du jeu
    HashMap<Integer, PlayerConfig> playerConfigs = new HashMap<Integer, PlayerConfig>();


    public GameMode gameMode;

    private ArrayList<PlayerAction> joueurs;
    public GameManager(){
        instance = this;

        //Creation des configs des joueurs
        playerConfigs.put(0, new PlayerConfig(KeyCode.Q, KeyCode.D, KeyCode.Z, KeyCode.S));
        playerConfigs.put(1, new PlayerConfig(KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN));
        playerConfigs.put(2, new PlayerConfig(KeyCode.C, KeyCode.B, KeyCode.F, KeyCode.V));
        playerConfigs.put(3, new PlayerConfig(KeyCode.G, KeyCode.J, KeyCode.Y, KeyCode.H));
        playerConfigs.put(4, new PlayerConfig(KeyCode.COMMA, KeyCode.COLON, KeyCode.K, KeyCode.PERIOD));
        playerConfigs.put(5, new PlayerConfig( KeyCode.L, KeyCode.STAR,KeyCode.P, KeyCode.M));
        HeapManager heapManager = new HeapManager();
        RuleManager ruleManager = new RuleManager();
        CardManager cardManager = new CardManager();
    }

    public ArrayList<PlayerAction> StartGame() throws Exception {
        CardManager.instance.CreateCards();
        PlayerAction.isGameOver = false;


        ArrayList<PlayerAction> joueurs = new ArrayList<PlayerAction>();

        for (int i = 0; i < nbJoueurs; i++){
            joueurs.add(new Player(playerConfigs.get(i)));
        }

        for (int i = 0; i < nbIA; i++){
            joueurs.add(new AIPlayer());
        }
        this.joueurs = joueurs;


        this.joueurs = CardManager.instance.GiveHandAndStack(joueurs);




        System.out.println("Heap card : " + HeapManager.instance.CardOnTop);

        return this.joueurs;
    }

    public void EndGame(){
        System.out.println("Fin de la partie");

        for ( PlayerAction joueur : joueurs) {
            if (joueur instanceof AIPlayer)
                ((AIPlayer) joueur).StopAI();
        }
    }


    public void DisplayEndWindow(){


        SceneManager.instance.DisplayPopUp();
        EndGame();
    }

    public ArrayList<PlayerAction> getPlayers(){
        return joueurs;
    }



}
