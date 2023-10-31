package ColorAddict;

import ColorAddict.Scenes.CustomScene;
import ColorAddict.Scenes.GameScene;
import ColorAddict.Scenes.MenuScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.HashMap;

public class SceneManager {

    public static SceneManager instance;
    private final Stage stage;

    private HashMap<String, CustomScene> scenes = new HashMap<String, CustomScene>();

    public SceneManager(Stage stage){
        this.stage = stage;
        if (instance == null)
            instance = this;

        scenes.put("MenuScene", new MenuScene());
        scenes.put("GameScene", new GameScene());

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        //Set app size to the screen size
        stage.setX(screenSize.getMinX());
        stage.setY(screenSize.getMinY());
        stage.setWidth(screenSize.getWidth());
        stage.setHeight(screenSize.getHeight());

        stage.setTitle("Hello!");

    }

    public void changeScene(String sceneName) {
        stage.hide();
        stage.setScene(scenes.get(sceneName).getScene());
        stage.show();
    }
}
