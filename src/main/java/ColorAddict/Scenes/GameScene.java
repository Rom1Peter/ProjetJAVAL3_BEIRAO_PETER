package ColorAddict.Scenes;

import ColorAddict.CardManager;
import ColorAddict.GameManager;
import ColorAddict.HeapManager;
import ColorAddict.Joueur;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class GameScene extends CustomScene{

    private GameManager gameManager;

    private double width, height;

    public GameScene() {

        this.gameManager = new GameManager();
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        //Set app size to the screen size
        width = screenSize.getWidth();
        height = screenSize.getHeight();

        System.out.println("width : " + width + " height : " + height);
    }

    public  void OnSceneEnter(){
        System.out.println("Je rentre dans le game");
        ArrayList<Joueur> joueurs = gameManager.StartGame();

        // Create grid pane which will contain all players
        GridPane grid = new GridPane();
        grid.maxWidth(1450);
        grid.maxHeight(1080);
        grid.setMinSize(1450, 1080);
        grid.setHgap(10);
        grid.setVgap(10);


        int row = 0;
        int col = 0;

        for(int i =0; i < joueurs.size(); i++){
            grid.add(joueurs.get(i), col, row);

            col++;
            if(col == 2){
                col = 0;
                row++;
            }
        }




        grid.setGridLinesVisible(true);

        Scene scene = new Scene(grid);
        this.scene = scene;
    }

    public  void OnSceneExit(){
        System.out.println("C pas moi qui kite le game c moi le game ki me kitty");
    }



}
