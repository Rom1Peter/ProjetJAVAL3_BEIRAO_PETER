package ColorAddict.Scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;



public abstract class CustomScene{

    protected Scene scene;

    public Scene getScene() {
        return this.scene;
    }

    public abstract void OnSceneEnter();

    public abstract void OnSceneExit();

}
