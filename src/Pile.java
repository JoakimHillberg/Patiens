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
                              "Discard♠"
    };

    private Scanner myScan = new Scanner(System.in);
    protected ArrayList<Card> cards;

    // Constructor
    public Pile(ArrayList<Card> cards) {
        this.cards = cards;
    }

    // Method
    public void moveCard() {
        this.cards.get(this.cards.size() - 1);

        for (int i = 0; i < piles.length; i++) {
            System.out.println((i + 1) + ": " + piles[i]);
        }

        System.out.print("Input index of pile to move card to: ");
        String pile = myScan.nextLine();

        while (true) {
            System.out.print("That is not a pile, input what pile to move the card to: ");
            pile = myScan.nextLine();
        }
    }
}
