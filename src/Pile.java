import java.util.ArrayList;
import java.util.Scanner;

public class Pile {
    // Attributes
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
        Card startTopCard = myGame.getTopCard(this);

        for (int i = 0; i < myGame.getPiles().length - 1; i++) {
            System.out.println((i + 1) + ": " + myGame.getPiles()[i]);
        }

        System.out.print("Input index of pile to move card to: ");
        int pileNr = myGame.tryParse(myScan.nextLine());

        while (pileNr < 1 || pileNr > 7) {
            System.out.print("Must input a valid index of a pile: ");
            pileNr = myGame.tryParse(myScan.nextLine());
        }

        if (moveIsValid(pileNr, startTopCard)) {
            if (this.cards.size() > 1) {
                this.cards.get(this.cards.indexOf(startTopCard) - 1).setHidden(false);
            }

            myGame.getMainPiles().get(pileNr - 1).cards.add(startTopCard);
            this.cards.remove(startTopCard);
        }
        else {
            System.out.println("That move is not valid.");
        }
    }

    public boolean moveIsValid(int pileNr, Card startTopCard) {
        boolean validMove = false;

        MainPile targetPile = myGame.getMainPiles().get(pileNr - 1);
        if (startTopCard.getNr() == myGame.getTopCard(targetPile).getNr() - 1) {
            String targetCardColor = myGame.getTopCard(targetPile).getColor();
            if (targetCardColor.equals("♡") || targetCardColor.equals("♢")) {
                if (startTopCard.getColor().equals("♣") || startTopCard.getColor().equals("♠")) {
                    validMove = true;
                }
            }
            else {
                if (startTopCard.getColor().equals("♡") || startTopCard.getColor().equals("♢")) {
                    validMove = true;
                }
            }
        }

        return validMove;
    }
}
