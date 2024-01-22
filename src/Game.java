import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    // Attributes
    private Scanner myScan = new Scanner(System.in);
    private Deck myDeck;
    private StockPile myStockPile = new StockPile(new ArrayList<>(), this);
    private ArrayList<MainPile> piles;
    private ArrayList<MainPile> mainPiles = new ArrayList<>();
    private ArrayList<DiscardPile> discardPiles = new ArrayList<>();
    private String[] menu = {
            "Draw card",
            "Stock card",
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
        /*System.out.println("┌A♢───┐");
        System.out.println("┌─────┐");
        System.out.println("|2    |");
        System.out.println("|  ♢  |");
        System.out.println("|    2|");
        System.out.println("└─────┘");*/

        while (!over) {
            printBoard();
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

    public Card getTopCard(Pile selectedPile) {
        if (!selectedPile.cards.isEmpty()) {
            return selectedPile.cards.get(selectedPile.cards.size() - 1);
        }
        else {
            return new Card(0, "0");
        }
    }

    public String cardToString(Card selectedCard) {
        String card = selectedCard.getNr() + selectedCard.getColor();
        card = card.replace("10","T");
        card = card.replace("11","J");
        card = card.replace("12","Q");
        card = card.replace("13","K");
        card = card.replace("1","A");
        return card;
    }

    public Pile getLargestMainPile(ArrayList<MainPile> pileList) {
        int largestCardCount = -1;
        Pile largestPile = new Pile(null, this);

        for (int i = 0; i < pileList.size(); i++) {
            if (largestCardCount < pileList.get(i).cards.size()) {
                largestCardCount = pileList.get(i).cards.size();
                largestPile = pileList.get(i);
            }
        }
        return largestPile;
    }

    public void printBoard() {
        System.out.print("XX " + cardToString(getTopCard(myStockPile)));
        System.out.print("    " + cardToString(getTopCard(discardPiles.get(0))));
        System.out.print(" " + cardToString(getTopCard(discardPiles.get(1))));
        System.out.print(" " + cardToString(getTopCard(discardPiles.get(2))));
        System.out.println(" " + cardToString(getTopCard(discardPiles.get(3))));

        Pile largestPile = getLargestMainPile(mainPiles);
        for (int i = 0; i < largestPile.cards.size(); i++) {
            for (int j = 0; j < 7; j++) {
                if (mainPiles.get(j).cards.size() >= i + 1) {
                    if (mainPiles.get(j).cards.size() == i + 1) {
                        System.out.print(cardToString(mainPiles.get(j).cards.get(i)) + " ");
                    }
                    else {
                        System.out.print("XX ");
                    }
                }
                else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }

    // Getters and Setters
    public StockPile getMyStockPile() {
        return myStockPile;
    }
}
