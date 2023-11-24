package Rules;

import ColorAddict.Card;

public abstract class GameRule {

    public abstract void RuleStart();
    public abstract boolean playCardCheck(Card card);
}
