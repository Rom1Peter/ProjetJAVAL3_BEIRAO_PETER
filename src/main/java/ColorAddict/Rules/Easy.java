package ColorAddict.Rules;

import ColorAddict.Card;
import ColorAddict.HeapManager;

public class Easy extends GameRule{

    @Override
    public void RuleStart() {
        nbColor = 5;
    }

    @Override
    public boolean playCardCheck(Card card) {
        //Joker
        //Joker
        if (card.getText() == "Joker" || HeapManager.instance.CardOnTop.getText() == "Joker") {
            return true;
        }


        //Carte normal
        if (card.getColor() == HeapManager.instance.CardOnTop.getColor() ||
                card.getText() == HeapManager.instance.CardOnTop.getText() ||
                card.getColor().toString() == HeapManager.instance.CardOnTop.getText() ||
                card.getText() == HeapManager.instance.CardOnTop.getColor().toString()) {
            return true;
        } else {
            return false;
        }
    }
}
