package ColorAddict.Scenes;

import ColorAddict.GameManager;
import ColorAddict.SceneManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuScene extends CustomScene{

    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 6;
    private int numPlayers = MIN_PLAYERS;

    private Label numPlayersLabel;
    public MenuScene() {
        VBox menu = new VBox(10);
        menu.setPadding(new Insets(20));
        menu.setAlignment(Pos.CENTER);
        Label titleLabel = new Label("Color Addict Game");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Label playersLabel = new Label("Number of Players:");
        playersLabel.setStyle("-fx-font-size: 16px;");
        HBox playersBox = new HBox(10);
        playersBox.setAlignment(Pos.CENTER);

        this.numPlayersLabel = new Label(Integer.toString(MIN_PLAYERS));
        this.numPlayersLabel.setStyle("-fx-font-size: 16px;");

        Button minusButton = new Button("-");
        minusButton.setOnAction(e -> decreasePlayers(numPlayersLabel));

        Button plusButton = new Button("+");
        plusButton.setOnAction(e -> increasePlayers(numPlayersLabel));

        playersBox.getChildren().addAll(minusButton, numPlayersLabel, plusButton);

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            try {
                startGame();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        menu.getChildren().addAll(titleLabel, playersLabel, playersBox, startButton);
        Scene scene = new Scene(menu, 400, 400);

        this.scene = scene;
    }

    private void decreasePlayers(Label numPlayersLabel) {
        if (numPlayers > MIN_PLAYERS) {
            numPlayers--;
            numPlayersLabel.setText(Integer.toString(numPlayers));
        }
    }
    private void increasePlayers(Label numPlayersLabel) {
        if (numPlayers < MAX_PLAYERS) {
            numPlayers++;
            numPlayersLabel.setText(Integer.toString(numPlayers));
        }
    }

    private void startGame() throws Exception {
        SceneManager.instance.changeScene("GameScene");
    }

    public  void OnSceneEnter(){
        System.out.println("Je rentre dans le Menu");
    }

    public  void OnSceneExit(){
        System.out.println("Je sors du menu hello kitty avec la valeur nbplayers : " + this.numPlayersLabel.getText() + "");
        GameManager.instance.nbJoueurs = Integer.valueOf(this.numPlayersLabel.getText());
    }
}
