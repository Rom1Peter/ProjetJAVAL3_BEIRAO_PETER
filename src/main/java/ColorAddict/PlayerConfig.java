package ColorAddict;

import javafx.scene.input.KeyCode;

public class PlayerConfig {

    KeyCode keyUp, keyDown, keyLeft, keyRight;

    public PlayerConfig( KeyCode keyLeft, KeyCode keyRight, KeyCode keyUp, KeyCode keyDown){
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }
}
