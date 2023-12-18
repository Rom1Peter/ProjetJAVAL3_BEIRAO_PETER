package ColorAddict.AI.State;

import ColorAddict.AI.AIPlayer;
import ColorAddict.AI.AIState;
import ColorAddict.RuleManager;

public class Thinking extends AIState {

    private int playableCardIndex = -1;
    private long lastActionTime = 0;
    private final long delay = 1000;
    private boolean isThinking = false;
    public Thinking(AIPlayer aiPlayer) {
        super(aiPlayer);
    }

    @Override
    public void OnEnter() {
        playableCardIndex = -1;
        lastActionTime = System.currentTimeMillis();
        isThinking = true;
    }

    @Override
    public void OnExit() {
        isThinking = false;
    }

    @Override
    public void Update() {
        if (!isThinking)
            return;

        if(System.currentTimeMillis() - lastActionTime < delay){
            return;
        }
        for(int i = 0; i < aiPlayer.hand.size(); i++){
            if(RuleManager.instance.checkPlayCard(aiPlayer.hand.get(i))){
                playableCardIndex = i;
                break;
            }
        }

        if(playableCardIndex == -1){
            aiPlayer.ChangeState(State.DRAWING_CARD);
            return;
        }

        if(playableCardIndex != -1 && playableCardIndex != aiPlayer.getIndexSelectedCard()) {
            if (playableCardIndex > aiPlayer.getIndexSelectedCard()) {
                for (int i = aiPlayer.getIndexSelectedCard(); i < playableCardIndex; i++) {
                    aiPlayer.augmenteCardIndex();
                }
            } else {
                for (int i = aiPlayer.getIndexSelectedCard(); i > playableCardIndex; i--) {
                    aiPlayer.diminueCardIndex();
                }
            }
        }

        if(playableCardIndex != -1 && playableCardIndex == aiPlayer.getIndexSelectedCard())
            aiPlayer.ChangeState(State.PLAYING_CARD);




    }
}
