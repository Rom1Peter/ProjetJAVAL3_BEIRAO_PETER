package ColorAddict.Rules;

import ColorAddict.Card;

public abstract class GameRule {

    public int nbColor;

    public abstract void RuleStart();
    public abstract boolean playCardCheck(Card card);

    public int getNbColors(){
        return nbColor;
    }

}
