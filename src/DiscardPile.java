/* A subclass for Pile where a Card the player discards ends up.
   Here Cards are sorted by color for each DiscardPile from ace to king to win.
   The player can only see the Card at the top of every DiscardPile. */
import java.util.ArrayList;

public class DiscardPile extends Pile {
    // Constructor
    public DiscardPile(ArrayList<Card> cards, Game myGame) {
        super(cards, myGame);
    }
}
