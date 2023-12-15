import java.util.ArrayList;

public class Game {
    // Attributes
    private Deck myDeck;
    private ArrayList<MainPile> piles;

    // Constructor
    public Game() {
        start();
    }

    // Methods
    public void start() {
        generateDeck();
    }

    public void generateDeck() {
        ArrayList<Card> cards = new ArrayList<>();
        String[] colors = {"diamonds", "hearts", "clubs", "spades"};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards.add(new Card(j + 1, colors[i]));
            }
        }

        myDeck = new Deck(cards);
    }

    public void generateBoard() {
        for (int i = 0; i < 7; i++) {

        }
    }
}
