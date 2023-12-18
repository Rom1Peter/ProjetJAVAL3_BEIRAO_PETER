package ColorAddict.AI.State;

import ColorAddict.AI.AIPlayer;
import ColorAddict.AI.AIState;

public class DrawingCard extends AIState {
    public DrawingCard(AIPlayer aiPlayer) {
        super(aiPlayer);
    }

    @Override
    public void OnEnter() {
        this.aiPlayer.PickCard();
        this.aiPlayer.ChangeState(State.THINKING);

    }

    @Override
    public void OnExit() {
    }

    @Override
    public void Update() {

    }
}
