package ColorAddict;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.File;

public class HeapManager extends Group {

    public static HeapManager instance = new HeapManager();
    public Card CardOnTop;

    private VBox vBox;
    public HeapManager(){
        if(instance != null){
            instance = this;
        }
    }

    public void SetCardOnTop(Card card){
        CardOnTop = card;

        if(vBox == null) {
            vBox = new VBox();
            vBox.setSpacing(10);
            vBox.setPadding(new Insets(0, 20, 10, 20));
            this.getChildren().add(vBox);
        }

        vBox.getChildren().clear();
        Group bigCard = card.getBigCardUI(0, 0);

        vBox.getChildren().add(bigCard);

    }
}
