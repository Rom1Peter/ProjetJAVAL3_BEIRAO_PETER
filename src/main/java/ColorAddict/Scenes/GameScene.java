package ColorAddict.Scenes;

import ColorAddict.*;
import ColorAddict.AI.AIPlayer;
import ColorAddict.Players.Player;
import ColorAddict.Players.PlayerAction;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.util.ArrayList;
import java.util.List;

import static ColorAddict.Players.PlayerAction.isGameOver;


public class GameScene extends CustomScene{

    private GameManager gameManager;

    private double width, height;

    public GameScene() {

        this.gameManager = new GameManager();
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        //Set app size to the screen size
        width = screenSize.getWidth();
        height = screenSize.getHeight();

        System.out.println("width : " + width + " height : " + height);
    }

    public  void OnSceneEnter() throws Exception {
        System.out.println("Je rentre dans le game");
            ArrayList<PlayerAction> joueurs = gameManager.StartGame();
        HBox borderPane = new HBox();

        // Create grid pane which will contain all players
        GridPane grid = new GridPane();
        grid.maxWidth(1450);
        grid.maxHeight(1080);
        grid.setMinSize(1250, 1080);
        grid.setHgap(10);
        grid.setVgap(10);


        int row = 0;
        int col = 0;

        for(int i =0; i < joueurs.size(); i++){
            grid.add(joueurs.get(i), col, row);

            col++;
            if(col == 2){
                col = 0;
                row++;
            }
        }

       /* borderPane.setLeft(grid);


        borderPane.setRight(HeapManager.instance);*/

        borderPane.getChildren().addAll(grid, HeapManager.instance);

        Scene scene = new Scene(borderPane);

        this.scene = scene;

        for (PlayerAction joueur : joueurs) {
            if(joueur instanceof Player)
                ((Player) joueur).AddKeyListening();
            if (joueur instanceof AIPlayer)
                ((AIPlayer) joueur).StartAI();
        }

        AddKeyListening();

    }

    public void DisplayPopUp(){
        System.out.println("Je display le popup");
        System.out.println("Game Over");
        Button goMenu = new Button("Menu");
        goMenu.setOnAction(e -> {
            try {
                SceneManager.instance.changeScene("MenuScene");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Button goGame = new Button("Rejouer");
        goGame.setOnAction(e -> {
            try {
                SceneManager.instance.changeScene("GameScene");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        List<Button> buttons = new ArrayList<Button>();
        buttons.add(goMenu);
        buttons.add(goGame);


        PopUpDisplay popupWindow = new PopUpDisplay("Game Over", buttons);

        Popup popup = new Popup();
        popup.getContent().add(popupWindow);

        popup.show(scene.getWindow());
        }


    public void DisplayEscapePopUp() {
        System.out.println("Displaying the popup");
        System.out.println("Game paused");

        PlayerAction.isGameOver = true;

        final Popup popup = new Popup();

        Button goMenu = new Button("Menu");
        goMenu.setOnAction(e -> {
            try {
                SceneManager.instance.changeScene("MenuScene");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button goGame = new Button("Resume");
        goGame.setOnAction(e -> {
            try {
                PlayerAction.isGameOver = false;

                popup.hide();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(goMenu, goGame);
        layout.setStyle("-fx-padding: 10;");

        popup.getContent().add(layout);


        popup.show(scene.getWindow());
    }




    public  void OnSceneExit(){
        System.out.println("C pas le game qui me quitte c'est moi qui quitte le game");


            //Reset the game
        GameManager old = gameManager;
        gameManager = new GameManager();

        gameManager.gameMode = old.gameMode;
        gameManager.nbJoueurs = old.nbJoueurs;
        gameManager.nbIA = old.nbIA;


    }

    public void AddKeyListening() throws NoSuchMethodException {
        SceneManager.instance.getCurrentScene().getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler(){
            @Override
            public void handle(javafx.event.Event event) {

                KeyEvent keyEvent = (KeyEvent) event;

                if(keyEvent.getCode() == KeyCode.ESCAPE && !isGameOver){
                    DisplayEscapePopUp();
                }
            }
        });


    }



}
