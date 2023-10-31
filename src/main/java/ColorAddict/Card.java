package ColorAddict;

import ColorAddict.Enums.CardColor;

public class Card {





    CardColor color;
    String text;

    public Card(CardColor color, String text){
        this.color = color;
        this.text = text;


    }

    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", text='" + text + '\'' +
                '}';
    }
}
