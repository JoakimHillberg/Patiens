import java.util.ArrayList;
import java.util.Random;

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
        ArrayList<MainPile> mainPiles = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            ArrayList<Card> mainCards = new ArrayList<>();

            for (int j = 0; j < i; j++) {
                Random generator = new Random();
                int randomNr = generator.nextInt(0, myDeck.cards.size());
                mainCards.add(myDeck.cards.get(randomNr));
                myDeck.cards.remove(randomNr);
            }

            mainPiles.add(new MainPile(mainCards));
        }
    }
}
