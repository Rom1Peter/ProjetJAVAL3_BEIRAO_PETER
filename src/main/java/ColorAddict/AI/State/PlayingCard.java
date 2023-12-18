package ColorAddict.AI.State;

import ColorAddict.AI.AIPlayer;
import ColorAddict.AI.AIState;

public class PlayingCard extends AIState {
    public PlayingCard(AIPlayer aiPlayer) {
        super(aiPlayer);
    }

    @Override
    public void OnEnter() {
        this.aiPlayer.playCard();
        this.aiPlayer.ChangeState(State.THINKING);

    }

    @Override
    public void OnExit() {
    }

    @Override
    public void Update() {

    }
}
