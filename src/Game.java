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
            "Main1",
            "Main2",
            "Main3",
            "Main4",
            "Main5",
            "Main6",
            "Main7",
            "StockPile"
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
                        myStockPile.moveCard();
                    }
                    else {
                        mainPiles.get(selectedPileNr - 1).moveCard();
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

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards.add(new Card(j + 1, colors[i], true));
            }
        }

        myDeck = new Deck(cards, this);
        Collections.shuffle(myDeck.cards);
    }

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

    public Card getTopCard(Pile selectedPile) {
        if (!selectedPile.cards.isEmpty()) {
            return selectedPile.cards.get(selectedPile.cards.size() - 1);
        }
        else {
            return new Card(0, "0", false);
        }
    }

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
                    /*if (mainPiles.get(j).cards.size() == i + 1) {

                    }
                    else {
                        System.out.print("XX ");
                    }*/
                }
                else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }

    public int tryParse(String input) {
        try {
            return Integer.parseInt(input);
        }
        catch(Exception e) {
            System.out.println("That is not an index of a pile.");
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
