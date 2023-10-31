package ColorAddict;

public class HeapManager {

    public static HeapManager instance = new HeapManager();
    public Card CardOnTop;

    public HeapManager(){
        if(instance != null){
            instance = this;
        }
    }

    public void SetCardOnTop(Card card){
        CardOnTop = card;
    }
}
