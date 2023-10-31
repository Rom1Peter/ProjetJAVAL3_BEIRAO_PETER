package ColorAddict;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneManager {
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 6;
    private int numPlayers = MIN_PLAYERS;
    private final Stage stage;


    public SceneManager(Stage stage){
        this.stage = stage;
    }

    public void createMainMenu() {
        VBox menu = new VBox(10);
        menu.setPadding(new Insets(20));
        menu.setAlignment(Pos.CENTER);
        Label titleLabel = new Label("Color Addict Game");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Label playersLabel = new Label("Number of Players:");
        playersLabel.setStyle("-fx-font-size: 16px;");
        HBox playersBox = new HBox(10);
        playersBox.setAlignment(Pos.CENTER);

        Label numPlayersLabel = new Label(Integer.toString(MIN_PLAYERS));
        numPlayersLabel.setStyle("-fx-font-size: 16px;");

        Button minusButton = new Button("-");
        minusButton.setOnAction(e -> decreasePlayers(numPlayersLabel));

        Button plusButton = new Button("+");
        plusButton.setOnAction(e -> increasePlayers(numPlayersLabel));

        playersBox.getChildren().addAll(minusButton, numPlayersLabel, plusButton);

        Button startButton = new Button("Start Game");
        menu.getChildren().addAll(titleLabel, playersLabel, playersBox, startButton);
        Scene scene = new Scene(menu, 400, 400);
        stage.setScene(scene);
        stage.show();
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



}
