import java.util.ArrayList;
import java.util.Scanner;

public class Pile {
    // Attributes
    private String[] piles = {"Main1",
                              "Main2",
                              "Main3",
                              "Main4",
                              "Main5",
                              "Main6",
                              "Main7",
                              "Discard♢",
                              "Discard♡",
                              "Discard♣",
                              "Discard♠",
    };

    private Scanner myScan = new Scanner(System.in);
    protected Game myGame;
    protected ArrayList<Card> cards;

    // Constructor
    public Pile(ArrayList<Card> cards, Game myGame) {
        this.cards = cards;
        this.myGame = myGame;
    }

    // Method
    public void moveCard() {
        Card startTopCard = this.cards.get(this.cards.size() - 1);

        for (int i = 0; i < piles.length; i++) {
            System.out.println((i + 1) + ": " + piles[i]);
        }

        System.out.print("Input index of pile to move card to: ");
        String pile = myScan.nextLine();
        int pileNr = tryParse(myScan.nextLine());

    }

    public boolean checkMove(int pileNr, Card startTopCard) {
        boolean validMove = false;

        System.out.print("That is not a pile, input what pile to move the card to: ");
        pileNr = tryParse(myScan.nextLine());

        if (pileNr > 1 || pileNr < 11) {
            validMove = true;
        }

        return validMove;
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
}
