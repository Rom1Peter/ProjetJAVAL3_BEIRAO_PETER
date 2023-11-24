package ColorAddict;

import ColorAddict.Enums.CardColor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;

public class Card {
    public static final String CARD_URL = "src/assets/UnoBlankCards.png";

    private CardColor color;
    private String text;

    public Card(CardColor color, String text) {
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

    public Group getCardUI(int posX, int posY) {
        Image image = new Image(new File(CARD_URL).toURI().toString());
        ImageView imageView = new ImageView(image);


        imageView.setFitHeight(100);
        imageView.setFitWidth(70);
        imageView.setX(posX);
        imageView.setY(posY);


        Text textUI = new Text(text);
        double textX = (imageView.getFitWidth() - textUI.getLayoutBounds().getWidth()) / 2;
        double textY = (imageView.getFitHeight() + textUI.getLayoutBounds().getHeight()) / 2;
        textUI.setX(textX + posX);
        textUI.setY(textY + posY);

        textUI.setFill(color.getColor());

        Group cardGroup = new Group();
        cardGroup.getChildren().addAll(imageView, textUI);
        return cardGroup;
    }

    public Group getBigCardUI(int posX, int posY) {
        Image image = new Image(new File(CARD_URL).toURI().toString());

        ImageView imageView = new ImageView(image);


        imageView.setFitHeight(100);
        imageView.setFitWidth(70);
        imageView.setX(posX);
        imageView.setY(posY);


        Text textUI = new Text(text);
        double textX = (imageView.getFitWidth() - textUI.getLayoutBounds().getWidth()) / 2;
        double textY = (imageView.getFitHeight() + textUI.getLayoutBounds().getHeight()) / 2;
        textUI.setX(textX + posX);
        textUI.setY(textY + posY);

        textUI.setFill(color.getColor());

        Group cardGroup = new Group();
        cardGroup.getChildren().addAll(imageView, textUI);
        return cardGroup;
    }

    public CardColor getColor() {
        return color;
    }

    public String getText() {
        return text;
    }
}
