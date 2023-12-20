package ColorAddict.Players;

import ColorAddict.PlayerConfig;
import ColorAddict.SceneManager;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Player extends PlayerAction{

    public PlayerConfig config;

    public Player(PlayerConfig playerConfig) throws Exception {
        this.config = playerConfig;

    }

    public void AddKeyListening() throws NoSuchMethodException {
        SceneManager.instance.getCurrentScene().getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler(){
            @Override
            public void handle(javafx.event.Event event) {
                if(isGameOver) {
                    System.out.println("GAME OVER");
                    return;
                }
                KeyEvent keyEvent = (KeyEvent) event;

                if(keyEvent.getCode() == config.keyLeft){
                    diminueCardIndex();
                }
                if(keyEvent.getCode() == config.keyRight){
                    augmenteCardIndex();
                }

                if(keyEvent.getCode() == config.keyUp){
                    playCard();
                }

                if(keyEvent.getCode() == config.keyDown){
                    PickCard();
                }
            }
        });


    }
}
