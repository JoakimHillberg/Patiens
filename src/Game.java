/* The class that contains the main code for the program.
   When "new Game()" runs in main the start() method runs to start the game. */
import java.util.*;

public class Game {
    // Attributes
    private Scanner myScan = new Scanner(System.in);
    private Deck myDeck;
    private StockPile myStockPile = new StockPile(new ArrayList<>(), this);
    private ArrayList<MainPile> mainPiles = new ArrayList<>();
    private ArrayList<DiscardPile> discardPiles = new ArrayList<>();
    private String[] colors = {"♢", "♡", "♣", "♠"};
    private String[] menu = {
            "Draw card",
            "Discard card",
            "Move card",
            "Quit",
    };
    private String[] pileList = {
            "Column 1",
            "Column 2",
            "Column 3",
            "Column 4",
            "Column 5",
            "Column 6",
            "Column 7",
            "Hand"
    };

    // Constructor
    public Game() {
        start();
    }

    // Methods
    // The method that stars the game when it runs.
    // Contains a While-loop that loops a Switch until the player wins or quits the game.
    // The Switch in the method contains the different options on the menu.
    public void start() {
        boolean over = false;
        generateDeck();
        generateBoard();

        while (!over) {
            printBoard();
            printMenu();
            String response = myScan.nextLine();

            int selectedPileNr = 0;
            switch (response) {
                case "1":
                    myDeck.drawCard();
                    break;

                case "2":
                    while (selectedPileNr > 8 || selectedPileNr < 1) {
                        for (int i = 0; i < pileList.length; i++) {
                            System.out.println((i + 1) + ": " + pileList[i]);
                        }

                        System.out.print("Input pile to discard top card from: ");
                        selectedPileNr = tryParse(myScan.nextLine());

                        if (selectedPileNr > 8 || selectedPileNr < 1) {
                            System.out.println("That is not a valid pile nr.");
                        }
                    }

                    if (selectedPileNr == 8) {
                        myStockPile.discardCard();
                    }
                    else {
                        mainPiles.get(selectedPileNr - 1).discardCard();
                    }
                    break;

                case "3":
                    while (selectedPileNr > 8 || selectedPileNr < 1) {
                        printBoard();
                        for (int i = 0; i < pileList.length; i++) {
                            System.out.println((i + 1) + ": " + pileList[i]);
                        }

                        System.out.print("Input pile to move card from: ");
                        selectedPileNr = tryParse(myScan.nextLine());

                        if (selectedPileNr > 8 || selectedPileNr < 1) {
                            System.out.println("That is not a valid pile nr.");
                        }
                    }

                    if (selectedPileNr == 8) {
                        if (!myStockPile.cards.isEmpty()) {
                            myStockPile.moveTopCard();
                        }
                        else {
                            System.out.println("That pile i empty.");
                        }
                    }
                    else {
                        if (!mainPiles.get(selectedPileNr - 1).cards.isEmpty()) {
                            mainPiles.get(selectedPileNr - 1).selectAndMoveStack();
                        }
                        else {
                            System.out.println("That pile is empty.");
                        }
                    }
                    break;

                case "4":
                    System.out.println("Quitting game");
                    over = true;
                    break;

                default:
                    System.out.println("That is not an option on the menu.");
                    break;
            }

            int discardCount = 0;

            for (int i = 0; i < discardPiles.size(); i++) {
                discardCount += discardPiles.get(i).cards.size();
            }

            if (discardCount == 52) {
                System.out.println("Congratulations, you won!");
                over = true;
            }
        }
    }

    // A method that prints the menu so the player knows what the options are.
    public void printMenu() {
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ": " + menu[i]);
        }

        System.out.print("Input what to do: ");
    }

    // A method that generates the Deck with two for-loopar that together go through all numbers for the four colors.
    // When the Cards are generated they are placed in Deck.
    public void generateDeck() {
        ArrayList<Card> cards = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards.add(new Card(j + 1, colors[i], true));
            }
        }

        myDeck = new Deck(cards, this);
        Collections.shuffle(myDeck.cards);
    }

    // A method that randomly generates what Cards end up in the MainPiles.
    // The amount of Cards in each Pile increases from 1 in the first to 7 in the last.
    // Cards are removed from Deck when placed in a MainPile.
    public void generateBoard() {
        for (int i = 1; i < 8; i++) {
            ArrayList<Card> mainCards = new ArrayList<>();

            for (int j = 0; j < i; j++) {
                Random generator = new Random();
                int randomNr = generator.nextInt(0, myDeck.cards.size());
                mainCards.add(myDeck.cards.get(randomNr));
                myDeck.cards.remove(randomNr);

                if (j + 1 == i) {
                    mainCards.get(j).setHidden(false);
                }
            }

            mainPiles.add(new MainPile(mainCards, this));
        }

        for (int i = 0; i < 4; i++) {
            discardPiles.add(new DiscardPile(new ArrayList<>(), this));
        }
    }

    // A method that picks out and sends back the Card on the top of the Pile that is sent in.
    public Card getTopCard(Pile selectedPile) {
        if (!selectedPile.cards.isEmpty()) {
            return selectedPile.cards.get(selectedPile.cards.size() - 1);
        }
        else {
            return new Card(0, "0", false);
        }
    }

    // A method that takes in a Card and converts its nr and color to a String.
    // The returned String only contains 2 characters(one for color and one for nr).
    public String cardToString(Card selectedCard) {
        String card = selectedCard.getNr() + selectedCard.getColor();

        if (selectedCard.isHidden()) {
            card = "XX";
        }
        else {
            card = card.replace("10","T");
            card = card.replace("11","J");
            card = card.replace("12","Q");
            card = card.replace("13","K");
            card = card.replace("1","A");
        }

        return card;
    }

    // A method that takes and ArrayList with MainPiles and returns the Pile with the most Cards.
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

    // A method that prints out the whole board for the player.
    public void printBoard() {
        System.out.print(cardToString(getTopCard(myDeck)) + " " + cardToString(getTopCard(myStockPile)));
        System.out.print("    " + cardToString(getTopCard(discardPiles.get(0))));
        System.out.print(" " + cardToString(getTopCard(discardPiles.get(1))));
        System.out.print(" " + cardToString(getTopCard(discardPiles.get(2))));
        System.out.println(" " + cardToString(getTopCard(discardPiles.get(3))));

        Pile largestPile = getLargestMainPile(mainPiles);
        for (int i = 0; i < largestPile.cards.size(); i++) {
            for (int j = 0; j < 7; j++) {
                if (mainPiles.get(j).cards.size() >= i + 1) {
                    System.out.print(cardToString(mainPiles.get(j).cards.get(i)) + " ");
                }
                else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }

    // A method takes a String and tries to use parseint() to make it an int.
    // If there is an error in the parseint() it sends back -1 otherwise it sends the number.
    public int tryParse(String input) {
        try {
            return Integer.parseInt(input);
        }
        catch(Exception e) {
            return -1;
        }
    }

    // Getters and Setters
    public StockPile getMyStockPile() {
        return myStockPile;
    }

    public ArrayList<MainPile> getMainPiles() {
        return mainPiles;
    }

    public String[] getPileList() {
        return pileList;
    }

    public ArrayList<DiscardPile> getDiscardPiles() {
        return discardPiles;
    }

    public String[] getColors() {
        return colors;
    }
}
