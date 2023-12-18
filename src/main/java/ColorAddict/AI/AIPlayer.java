package ColorAddict.AI;

import ColorAddict.AI.State.*;
import ColorAddict.Players.PlayerAction;
import javafx.animation.AnimationTimer;

import java.util.HashMap;

public class AIPlayer extends PlayerAction {

    private HashMap<State, AIState> states;
    private AIState currentState;
    private AnimationTimer gameLoop;
    private boolean running = false;

    public AIPlayer() {
        states = new HashMap<>();
        states.put(State.THINKING, new Thinking(this));
        states.put(State.PLAYING_CARD, new PlayingCard(this));
        states.put(State.DRAWING_CARD, new DrawingCard(this));
        states.put(State.FINISHED, new Finished(this));

        ChangeState(State.THINKING);
        initializeGameLoop();
    }

    private void initializeGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(running)
                    Update();
            }
        };
        gameLoop.start();
    }

    private void Update() {
        if (currentState != null) {
            currentState.Update();
        }
    }

    public void ChangeState(State state) {
        if (currentState != null) {
            currentState.OnExit();
        }

        System.out.println("Going from " + (currentState != null ? currentState.getClass().getSimpleName() : "null") + " to " + state);
        currentState = states.get(state);
        if (currentState != null) {
            currentState.OnEnter();
        }

    }

    public void StopAI() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }

    public void StartAI() {
        running = true;
    }
}
