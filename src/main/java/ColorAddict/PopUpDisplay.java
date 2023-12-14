package ColorAddict;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;

public class PopUpDisplay extends StackPane {

    private static final int WIDTH = 300;

    private static final int HEIGHT = 200;

    private Text bigText;
    private VBox buttonContainer;

    public PopUpDisplay(String text, List<Button> buttons) {

        this.setPrefSize(WIDTH, HEIGHT);

        bigText = new Text(text);
        bigText.setStyle("-fx-font-size: 18;");

        buttonContainer = new VBox(10);
        buttonContainer.setPadding(new javafx.geometry.Insets(10));

        buttons.forEach(button -> {
            buttonContainer.getChildren().add(button);
        });
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

        this.getChildren().addAll(bigText, buttonContainer);
    }

    public void setBigText(String newText) {
        bigText.setText(newText);
    }

    public StackPane getPopUpUI(int posX, int posY){
        this.setLayoutX(posX);
        this.setLayoutY(posY);
        return this;
    }
}
