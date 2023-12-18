package ColorAddict;

import ColorAddict.Players.PlayerAction;
import ColorAddict.Rules.GameRule;
import ColorAddict.Rules.Normal;

import java.util.Objects;

public class RuleManager {
    public static RuleManager instance = new RuleManager();

    public GameRule currentRule = new Normal();

    public RuleManager(){
        if(instance != null){
            instance = this;
        }else {
            instance = this;
        }
    }

    public boolean checkPlayCard(Card card){

        return currentRule.playCardCheck(card);
    }

    public void setRule(GameRule rule){
        currentRule = rule;
        currentRule.RuleStart();
        System.out.println("On a" + getNbColors());

    }

    public boolean checkIfGameStuck(){
        Card cardOnTop = HeapManager.instance.CardOnTop;
        for (PlayerAction player : GameManager.instance.getPlayers()) {
            if (player.hand.size() == 3) {
                for (Card card : player.hand) {
                    System.out.println(card + " " + cardOnTop);
                    if (currentRule.playCardCheck(card)) {
                        System.out.println("Game not stuck");
                        return false;
                    }
                }
            }else {
                System.out.println("Game not stuck");
                return false;
            }
        }
        System.out.println("Game stuck");
        return true;
    }

    public int getNbColors(){
        return currentRule.getNbColors();
    }

}
