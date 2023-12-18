package ColorAddict;

import javafx.scene.input.KeyCode;

public class PlayerConfig {

    public KeyCode keyUp;
    public KeyCode keyDown;
    public KeyCode keyLeft;
    public KeyCode keyRight;

    public PlayerConfig( KeyCode keyLeft, KeyCode keyRight, KeyCode keyUp, KeyCode keyDown){
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }
}
