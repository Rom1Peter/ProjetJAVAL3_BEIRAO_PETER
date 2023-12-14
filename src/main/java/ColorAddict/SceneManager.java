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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.security.auth.callback.Callback;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.function.Function;

public class SceneManager {

    public static SceneManager instance;
    private final Stage stage;

    //Dictionnaire de scene lié à leurs nom
    private HashMap<String, CustomScene> scenes = new HashMap<String, CustomScene>();
    //Scene actuelle
    private CustomScene currentScene;

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

    public void changeScene(String sceneName) throws Exception {
        stage.hide();

        CustomScene newCustomScene = scenes.get(sceneName);

        if(currentScene != null){
            currentScene.OnSceneExit();

        }

        currentScene = newCustomScene;

        currentScene.OnSceneEnter();

        Scene newScene = scenes.get(sceneName).getScene();

        stage.setScene(newScene);
        stage.show();

    }

    public CustomScene getCurrentScene(){
        return currentScene;
    }


    public void DisplayPopUp(){
        ((GameScene) this.scenes.get("GameScene")).DisplayPopUp();
    }
}
