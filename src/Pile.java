/* Superclass for all types of Piles.
   Contains an ArrayList with all the Cards that are in the Pile. */
import java.util.ArrayList;
import java.util.Scanner;

public class Pile {
    // Attributes
    protected Scanner myScan = new Scanner(System.in);
    protected Game myGame;
    protected ArrayList<Card> cards;

    // Constructor
    public Pile(ArrayList<Card> cards, Game myGame) {
        this.cards = cards;
        this.myGame = myGame;
    }

    // Method
    // A method that moves a selected Card from this Pile to the one the player chooses.
    // If the player wants to move more than one card, selectedCard is the one at the bottom of that small pile.
    // When a Card is moved the Card beneath is changed so that hidden = false.
    // The player cannot move a Card where the attribute hidden = true.
    public void moveCard(Card selectedCard) {
        myGame.printBoard();
        for (int i = 0; i < myGame.getPileList().length - 1; i++) {
            System.out.println((i + 1) + ": " + myGame.getPileList()[i]);
        }

        System.out.print("Input index of pile to move card to: ");
        int pileNr = myGame.tryParse(myScan.nextLine());

        while (pileNr < 1 || pileNr > 7) {
            System.out.print("Must input a valid index of a pile: ");
            pileNr = myGame.tryParse(myScan.nextLine());
        }

        if (moveIsValid(pileNr, selectedCard) && !selectedCard.isHidden()) {
            if (this.cards.indexOf(selectedCard) > 0) {
                this.cards.get(this.cards.indexOf(selectedCard) - 1).setHidden(false);
            }

            int selectedCardIndex = this.cards.indexOf(selectedCard);
            ArrayList<Card> selectedCards = new ArrayList<>(this.cards.subList(selectedCardIndex, this.cards.size()));
            myGame.getMainPiles().get(pileNr - 1).cards.addAll(selectedCards);
            this.cards.removeAll(selectedCards);
        }
        else {
            System.out.println("That move is not valid.");
        }
    }

    // A method that controls if a given move is valid.
    // Is valid if the Pile alternates colors and the number beneath is 1 digit higher.
    // If a MainPile is empty it is also valid to place a king(nr 13) on that MainPile.
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

    // A method that takes the Card on the top of the Pile that calls the method and tries to send it to the same colored DiscardPile.
    // The Card is sent to the DiscardPile for its specific color if the number is 1 higher than the Card currently on top of the DiscardPile.
    // If the DiscardPile is empty it needs an ace placed on it.
    // Also says if the player is trying to move a Card from an empty Pile.
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
