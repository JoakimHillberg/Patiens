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

        for (int i = 0; i < myGame.getPileList().length - 1; i++) {
            System.out.println((i + 1) + ": " + myGame.getPileList()[i]);
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
        if (targetPile.cards.isEmpty() && startTopCard.getNr() == 13) {
            validMove = true;
        }
        else if (startTopCard.getNr() == myGame.getTopCard(targetPile).getNr() - 1) {
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

    public void discardCard() {
        Card selectedCard = myGame.getTopCard(this);

        if (this.cards.isEmpty()) {
            System.out.println("There are no cards in that pile.");
        }

        for (int i = 0; i < myGame.getDiscardPiles().size(); i++) {
            if (myGame.getColors()[i].equals(selectedCard.getColor())) {
                boolean correctFirstCard = myGame.getDiscardPiles().get(i).cards.isEmpty() && selectedCard.getNr() == 1;
                boolean correctNextCard = (myGame.getTopCard(myGame.getDiscardPiles().get(i)).getNr() == selectedCard.getNr() - 1);

                if (correctFirstCard || correctNextCard) {
                    myGame.getDiscardPiles().get(i).cards.add(selectedCard);
                    this.cards.remove(selectedCard);
                    myGame.getTopCard(this).setHidden(false);
                }
                else {
                    System.out.println("That card cannot be discarded right now.");
                }
            }
        }
    }
}
