package ColorAddict;

import ColorAddict.Enums.CardColor;
import ColorAddict.Players.PlayerAction;

import java.util.ArrayList;
import java.util.Collections;

public class CardManager {
    private int NBCARDS_MAX = 0;
    private final int NBCARDS_MAX_JOKER = 8;
    private final int NBCARDS_MAX_PER_COLOR = 6;
    private final int NBCARDS_MAX_PER_HAND = 3;

    public ArrayList<Card> cards = new ArrayList<Card>();
    private int nbCards = 0;

    public static CardManager instance = new CardManager();


    public CardManager(){
        if(instance != null){
            instance = this;
        }else {
            instance = this;
        }
    }
    public void CreateCards() {
        int maxCards = RuleManager.instance.getNbColors() * NBCARDS_MAX_PER_COLOR;
        CardColor[] colors = CardColor.values();
        int nbColors = RuleManager.instance.getNbColors();

        for (int i = 0; i < nbColors; i++) {
            createColorCards(colors[i], colors, nbColors);
        }

        createJokers(NBCARDS_MAX_JOKER);
    }

    private void createColorCards(CardColor color, CardColor[] colors, int nbColors) {
        int nbCardsPerColor = 0;

        while (nbCardsPerColor < NBCARDS_MAX_PER_COLOR) {
            for (int i = 0; i < nbColors; i++) {
                if (!colors[i].equals(color)) {
                    cards.add(CreateCard(color, colors[i].toString()));
                    nbCardsPerColor++;
                }
                if (nbCardsPerColor == NBCARDS_MAX_PER_COLOR) break;
            }
        }
    }

    private void createJokers(int maxJokers) {
        for (int i = 0; i < maxJokers; i++) {
            cards.add(CreateJoker());
        }
        System.out.println("Created " + maxJokers + " jokers");
    }


    public Card CreateCard(CardColor color, String text){
        //System.out.println("Just create a card with the color : " + color + "and the text : " + text);
        return new Card(color, text);
    }

    public Card CreateJoker(){
        return new Card(CardColor.BLACK, "Joker");
    }

    public ArrayList<PlayerAction> GiveHandAndStack(ArrayList<PlayerAction> j){

        boolean isImpair = false;
        Card cardImpair = null;

        int nbCardsPerStack;

        //On mélange les cartes
        Collections.shuffle(cards);

        Card heapCard = PickCard();

        HeapManager.instance.SetCardOnTop(heapCard);

        //On donne les cartes pour les mains aux joueurs
        for(PlayerAction joueur : j){
            ArrayList<Card> hand = new ArrayList<Card>();
            ArrayList<Card> stack = new ArrayList<Card>();

            for(int i = 0; i < NBCARDS_MAX_PER_HAND; i++){
                hand.add(PickCard());
            }

            joueur.SetMain(hand);
        }



        //Si impair on rend le jeu pair en prennant une carte
        if(j.size() % 2 != 0){
            isImpair = true;
            cardImpair = PickCard();
        }



        nbCardsPerStack = cards.size() / j.size();


        //On donne les cartes pour les piles aux joueurs
        for(PlayerAction joueur : j){
            ArrayList<Card> stack = new ArrayList<Card>();

            for(int i = 0; i < nbCardsPerStack; i++){
                stack.add(PickCard());
            }

            joueur.SetPioche(stack);
        }

        return j;
    }

    public void RedispatchCards(ArrayList<PlayerAction> players) {

        cards.clear();


        for (PlayerAction player : players) {
            for (Card card : player.hand) {
                cards.add(card);
            }
            for (Card card : player.stack) {
                cards.add(card);
            }
            player.deleteAllCards();

        }

        Collections.shuffle(cards);

        HeapManager.instance.SetCardOnTop(PickCard());


        for (PlayerAction player : players) {
            ArrayList<Card> hand = new ArrayList<Card>();

            for(int i = 0; i < NBCARDS_MAX_PER_HAND; i++){
                hand.add(PickCard());
            }

            player.SetMain(hand);
        }

        int nbCardsPerStack = cards.size() / players.size();

        for(PlayerAction player : players){
            ArrayList<Card> stack = new ArrayList<Card>();

            for(int i = 0; i < nbCardsPerStack; i++){
                stack.add(PickCard());
            }

            player.SetPioche(stack);
        }





    }

    public Card PickCard(){
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }
}
