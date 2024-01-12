import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    // Attributes
    private Deck myDeck;
    private ArrayList<MainPile> piles;
    ArrayList<MainPile> mainPiles = new ArrayList<>();

    // Constructor
    public Game() {
        start();
    }

    // Methods
    public void start() {
        generateDeck();
        generateBoard();
    }

    public void generateDeck() {
        ArrayList<Card> cards = new ArrayList<>();
        String[] colors = {"♢", "♡", "♣", "♠"};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards.add(new Card(j + 1, colors[i]));
            }
        }

        myDeck = new Deck(cards);
    }

    public void generateBoard() {
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

    public int tryParse(String input) {
        try {
            int number = Integer.parseInt(input);
            return number;
        }
        catch(Exception e) {
            System.out.println("That is not an index of a list.");
            return -1;
        }
    }
}
