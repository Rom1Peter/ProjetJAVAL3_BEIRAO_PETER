package ColorAddict;

import ColorAddict.Enums.GameMode;

public class GameManager {

    public static GameManager instance;

    public int nbJoueurs;
    public int nbIA;

    public GameMode gameMode;

    public GameManager(){
        instance = this;
    }


}
