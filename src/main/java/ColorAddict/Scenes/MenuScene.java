package ColorAddict.Scenes;

import ColorAddict.GameManager;
import ColorAddict.RuleManager;
import ColorAddict.SceneManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class MenuScene extends CustomScene{

    private static final int MIN_PLAYERS = 1;
    private static final int MAX_PLAYERS = 6;

    private static final int MIN_AI_PLAYERS = 0;
    private static final int MAX_AI_PLAYERS = 4;
    private int numPlayers = MIN_PLAYERS;

    private int numAIPlayers = MIN_AI_PLAYERS;



    private Label numPlayersLabel;
    private ImageView[] playerImageViews;
    private Button minusButtonPlayer;
    private Button plusButtonPlayer;

    private Label numAIPlayersLabel;
    private Button minusButtonAIPlayer;
    private Button plusButtonAIPlayer;

    private ComboBox<String> comboBox;
    public MenuScene() {
        StackPane root = new StackPane();
        Pane PlayerImg = new Pane();
        Media media = new Media(new File("src/assets/Menu.mp4").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitHeight(SceneManager.screenSize.getHeight());
        mediaView.setFitWidth(SceneManager.screenSize.getWidth());

        VBox menu = new VBox(10);
        menu.setPadding(new Insets(20));
        menu.setAlignment(Pos.CENTER);
        ImageView titleImageView = new ImageView(new Image(new File("src/assets/MenuLogo.png").toURI().toString()));

        ImageView player1ImageView = new ImageView(new Image(new File("src/assets/Menu/Rocket/Player1.png").toURI().toString()));
        player1ImageView.setX(425);
        player1ImageView.setY(700);

        ImageView player2ImageView = new ImageView(new Image(new File("src/assets/Menu/Rocket/Player2.png").toURI().toString()));
        player2ImageView.setX(150);
        player2ImageView.setY(400);

        ImageView player3ImageView = new ImageView(new Image(new File("src/assets/Menu/Rocket/Player3.png").toURI().toString()));
        player3ImageView.setX(425);
        player3ImageView.setY(100);

        ImageView player4ImageView = new ImageView(new Image(new File("src/assets/Menu/Rocket/Player4.png").toURI().toString()));
        player4ImageView.setX(1025);
        player4ImageView.setY(100);

        ImageView player5ImageView = new ImageView(new Image(new File("src/assets/Menu/Rocket/Player5.png").toURI().toString()));
        player5ImageView.setX(1300);
        player5ImageView.setY(400);

        ImageView player6ImageView = new ImageView(new Image(new File("src/assets/Menu/Rocket/Player6.png").toURI().toString()));
        player6ImageView.setX(1025);
        player6ImageView.setY(700);

        this.playerImageViews = new ImageView[]{player1ImageView, player2ImageView, player3ImageView, player4ImageView, player5ImageView, player6ImageView};

        Label playersLabel = new Label("Number of Players:");
        playersLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        playersLabel.setStyle("-fx-font-size: 16px;");
        HBox playersBox = new HBox(10);
        HBox AIPlayersBox = new HBox(10);
        playersBox.setAlignment(Pos.CENTER);


        //Number of players
        this.numPlayersLabel = new Label(Integer.toString(MIN_PLAYERS));
        this.numPlayersLabel.setStyle("-fx-font-size: 16px;");
        this.numPlayersLabel.setTextFill(javafx.scene.paint.Color.WHITE);

        Button minusButton = new Button("-");
        minusButton.setOnAction(e -> decreasePlayers(numPlayersLabel));

        Button plusButton = new Button("+");
        plusButton.setOnAction(e -> increasePlayers(numPlayersLabel));


        this.minusButtonPlayer = minusButton;
        this.plusButtonPlayer = plusButton;

        playersBox.getChildren().addAll(minusButton, numPlayersLabel, plusButton);

        //Number of AI players
        Label AIPlayersLabel = new Label("Number of AI Players:");
        AIPlayersLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        AIPlayersLabel.setStyle("-fx-font-size: 16px;");
        AIPlayersBox.setAlignment(Pos.CENTER);
        Label numAIPlayersLabel = new Label("0");
        numAIPlayersLabel.setStyle("-fx-font-size: 16px;");
        numAIPlayersLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        Button minusAIButton = new Button("-");
        minusAIButton.setOnAction(e -> decreaseAIPlayers(numAIPlayersLabel));

        Button plusAIButton = new Button("+");
        plusAIButton.setOnAction(e -> increaseAIPlayers(numAIPlayersLabel));

        this.numAIPlayersLabel = numAIPlayersLabel;
        this.minusButtonAIPlayer = minusAIButton;
        this.plusButtonAIPlayer = plusAIButton;


        AIPlayersBox.getChildren().addAll(minusAIButton, numAIPlayersLabel, plusAIButton);

        ComboBox<String> comboBox = new ComboBox<>();

        comboBox.getItems().addAll("Easy", "Normal");

        comboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            // Handle rule change
            System.out.println("Selected Rule: " + newValue);

        });

        comboBox.setValue("Normal");

        this.comboBox = comboBox;
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            try {
                startGame();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        PlayerImg.getChildren().addAll(player1ImageView, player2ImageView, player3ImageView, player4ImageView, player5ImageView, player6ImageView);
        menu.getChildren().addAll(titleImageView, playersLabel, playersBox, AIPlayersLabel, AIPlayersBox, comboBox, startButton);
        root.getChildren().addAll(mediaView,PlayerImg,menu);
        RefreshPlayerDisplay();
        RefreshButtonDisplay();
        Scene scene = new Scene(root, 400, 400);

        this.scene = scene;
    }

    private void decreasePlayers(Label numPlayersLabel) {
        if (numPlayers > MIN_PLAYERS && numPlayers + numAIPlayers > MIN_PLAYERS) {
            numPlayers--;
            numPlayersLabel.setText(Integer.toString(numPlayers));
            RefreshPlayerDisplay();
            RefreshButtonDisplay();
        }
    }
    private void increasePlayers(Label numPlayersLabel) {
        if (numPlayers < MAX_PLAYERS && numPlayers + numAIPlayers < MAX_PLAYERS) {
            numPlayers++;
            numPlayersLabel.setText(Integer.toString(numPlayers));
            RefreshPlayerDisplay();
            RefreshButtonDisplay();
        }
    }

    private void decreaseAIPlayers(Label numAIPlayersLabel) {
        if (numAIPlayers > MIN_AI_PLAYERS && numPlayers + numAIPlayers > MIN_PLAYERS) {
            numAIPlayers--;
            numAIPlayersLabel.setText(Integer.toString(numAIPlayers));
            RefreshPlayerDisplay();
            RefreshButtonDisplay();
        }
    }

    private void increaseAIPlayers(Label numAIPlayersLabel) {
        if (numAIPlayers < MAX_AI_PLAYERS && numPlayers + numAIPlayers < MAX_PLAYERS) {
            numAIPlayers++;
            numAIPlayersLabel.setText(Integer.toString(numAIPlayers));
            RefreshPlayerDisplay();
            RefreshButtonDisplay();
        }
    }



    private void RefreshPlayerDisplay(){
        for(int i = 0; i < this.playerImageViews.length; i++){
            if(i < this.numPlayers){
                this.playerImageViews[i].setVisible(true);
            }else{
                this.playerImageViews[i].setVisible(false);
            }
        }
    }

    private void RefreshButtonDisplay(){
        if(this.numPlayers == MIN_PLAYERS){
            this.minusButtonPlayer.setDisable(true);
        }else{
            this.minusButtonPlayer.setDisable(false);
        }

        if(this.numPlayers == MAX_PLAYERS || this.numPlayers + this.numAIPlayers == MAX_PLAYERS){
            this.plusButtonPlayer.setDisable(true);
        }else{
            this.plusButtonPlayer.setDisable(false);
        }

        if(this.numAIPlayers == MIN_AI_PLAYERS){
            this.minusButtonAIPlayer.setDisable(true);
        }else{
            this.minusButtonAIPlayer.setDisable(false);
        }

        if(this.numAIPlayers == MAX_AI_PLAYERS || this.numPlayers + this.numAIPlayers == MAX_PLAYERS){
            this.plusButtonAIPlayer.setDisable(true);
        }else{
            this.plusButtonAIPlayer.setDisable(false);
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
        GameManager.instance.nbIA = Integer.valueOf(this.numAIPlayersLabel.getText());
        SetRule(this.comboBox.getValue());
    }

    public void SetRule(String rule){
        switch (rule) {
            case "Easy":
                RuleManager.instance.setRule(new ColorAddict.Rules.Easy());
                break;
            case "Normal":
                RuleManager.instance.setRule(new ColorAddict.Rules.Normal());
                break;
        }
    }
}
