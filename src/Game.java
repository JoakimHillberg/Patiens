import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    // Attributes
    private Scanner myScan = new Scanner(System.in);
    private Deck myDeck;
    private StockPile myDrawPile = new StockPile(new ArrayList<>(), this);
    private ArrayList<MainPile> piles;
    private ArrayList<MainPile> mainPiles = new ArrayList<>();
    private ArrayList<DiscardPile> discardPiles = new ArrayList<>();
    private String[] menu = {
            "Draw card",
            "Discard card",
            "Move card",
    };

    // Constructor
    public Game() {
        start();
    }

    // Methods
    public void start() {
        boolean over = false;
        generateDeck();
        generateBoard();

        while (!over) {
            printMenu();
            String response = myScan.nextLine();

            switch (response) {
                case "1":
                    myDeck.moveCard();
                    break;

                case "2":
                    break;

                case "3":
                    break;

                default:
                    System.out.println("That is not an option on the menu.");
                    break;
            }

        }
    }

    public void printMenu() {
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ": " + menu[i]);
        }

        System.out.print("Input what to do: ");
    }

    public void generateDeck() {
        ArrayList<Card> cards = new ArrayList<>();
        String[] colors = {"♢", "♡", "♣", "♠"};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards.add(new Card(j + 1, colors[i]));
            }
        }

        myDeck = new Deck(cards, this);
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

            mainPiles.add(new MainPile(mainCards, this));
        }

        for (int i = 0; i < 4; i++) {
            discardPiles.add(new DiscardPile(new ArrayList<>(), this));
        }
    }

    public String cardToString(Card selectedCard) {
        String card = selectedCard.getNr() + selectedCard.getColor();
        card.replace("11","J");
        card.replace("12","D");
        card.replace("13","K");
        card.replace("1","A");
        return card;
    }

    public void printBoard() {

    }

    // Getters and Setters
    public StockPile getMyDrawPile() {
        return myDrawPile;
    }
}
