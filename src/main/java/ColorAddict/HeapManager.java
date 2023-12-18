package ColorAddict;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.File;

public class HeapManager extends Group {

    private static final String BG_URL = "src/assets/Table.jpg";
    public static HeapManager instance = new HeapManager();

    public Card CardOnTop;

    private StackPane heapStackPane;
    public HeapManager(){
        if(instance != null){
            instance = this;
        }else {
            instance = this;
        }
    }

    public void SetCardOnTop(Card card){
        CardOnTop = card;

        if(heapStackPane == null) {
            heapStackPane = new StackPane();

            heapStackPane.setPadding(new Insets(0, 0 , 0, -100));
            this.getChildren().add(heapStackPane);
        }

        heapStackPane.getChildren().clear();
        ImageView imageView = new ImageView(new Image(new File(BG_URL).toURI().toString()));
        imageView.setFitHeight(1080);
        imageView.setFitWidth(500);
        Group bigCard = card.getBigCardUI(0, 0);
        heapStackPane.getChildren().addAll(imageView, bigCard);

    }

    public void CheckStuckedGame(){
        if (RuleManager.instance.checkIfGameStuck()) {
            System.out.println("Game stuck");
            CardManager.instance.RedispatchCards(GameManager.instance.getPlayers());
        }
    }
}
