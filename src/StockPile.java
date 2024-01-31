/* A subclass for Pile that is the players hand.
   When the player pulls a card it ends up in the ArrayList that StockPile(this class) has.
   The player always sees the Card at the top of the StockPile */
import java.util.ArrayList;

public class StockPile extends Pile {
    // Constructor
    public StockPile(ArrayList<Card> cards, Game myGame) {
        super(cards, myGame);
    }

    // Methods
    // Take the Card at the top of StockPile and calls the moveCard() method in Pile.
    public void moveTopCard() {
        moveCard(myGame.getTopCard(this));
    }
}
