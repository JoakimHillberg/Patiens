/* A subclass for Pile that makes up the board with seven MainPiles where random Cards are placed.
   At the start the player only sees the Card at the top of each MainPile.
   During the game the player also sees the Cards that they have moved. */
import java.util.ArrayList;

public class MainPile extends Pile {
    // Constructor
    public MainPile(ArrayList<Card> cards, Game myGame) {
        super(cards, myGame);
    }

    // Methods
    // Method that asks the player how many Cards they want to move from MainPile.
    // Takes the Card at the bottom of the Pile player wants to send and calls the moveCard() method.
    public void selectAndMoveStack() {
        Card selectedCard;
        System.out.print("Input how many cards to take from the top och the pile: ");
        int cardPosition = myGame.tryParse(myScan.nextLine());

        while (cardPosition > this.cards.size() || cardPosition <= 0) {
            System.out.print("Not possible, Input valid amount of cards: ");
            cardPosition = myGame.tryParse(myScan.nextLine());
        }

        selectedCard = this.cards.get(this.cards.size() - cardPosition);

        moveCard(selectedCard);
    }
}
