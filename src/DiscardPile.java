/* En subclass till Pile där kort placeras när spelaren lägger undan de(discard).
   Här sorteras kort med varje färg i en egen DiscardPile från ess till kung för att vinna.
   Spelaren kan endast se det kortet som finns högst upp i varje DiscardPile. */
import java.util.ArrayList;

public class DiscardPile extends Pile {
    // Constructor
    public DiscardPile(ArrayList<Card> cards, Game myGame) {
        super(cards, myGame);
    }
}
