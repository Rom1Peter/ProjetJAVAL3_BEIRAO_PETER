package ColorAddict.AI;

public abstract class AIState {

    protected AIPlayer aiPlayer = null;

    public AIState(AIPlayer aiPlayer) {
        this.aiPlayer = aiPlayer;
    }

    public abstract void OnEnter();
    public abstract void OnExit();
    public abstract void Update();

}
