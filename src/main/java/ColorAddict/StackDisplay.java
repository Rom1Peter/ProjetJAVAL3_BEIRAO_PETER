package ColorAddict;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;

public class StackDisplay {

    public static final String CARD_URL = "src/assets/UnoBlankCards.png";

    private Group stackGroup;
    private Text textUI;

    public StackDisplay(int numberOfCards, int posX, int posY){

        Image image = new Image(new File(CARD_URL).toURI().toString());
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(100);
        imageView.setFitWidth(70);
        imageView.setX(posX);
        imageView.setY(posY);

        Text textUI = new Text(String.valueOf(numberOfCards));
        textUI.setFont(new Font("Arial", 15));
        double textX = (imageView.getFitWidth() - textUI.getLayoutBounds().getWidth()) / 2;
        double textY = (imageView.getFitHeight() + textUI.getLayoutBounds().getHeight()) / 2;
        textUI.setX(textX + posX);
        textUI.setY(textY + posY);

        this.textUI = textUI;


        Group stackGroup = new Group();
        stackGroup.getChildren().addAll(imageView, textUI);
        this.stackGroup = stackGroup;



    }

    public Group getStackUI(){
        return this.stackGroup;
    }

    public void setStackUI(int numberOfCards){
        this.textUI.setText(String.valueOf(numberOfCards));
    }
}
