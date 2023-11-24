package ColorAddict;

import Rules.GameRule;
import Rules.Normal;

public class RuleManager {
    public static RuleManager instance = new RuleManager();

    public GameRule currentRule = new Normal();

    public RuleManager(){
        if(instance != null){
            instance = this;
        }
    }

    public boolean checkPlayCard(Card card){

        return currentRule.playCardCheck(card);
    }

    public void setRule(GameRule rule){
        currentRule = rule;
        rule.RuleStart();
    }

}
