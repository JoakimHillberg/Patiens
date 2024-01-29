/* En subclass till Pile som är användarens hand.
   När användaren drar kort så hamnar de i den ArrayList som StockPile har.
   Spelaren ser alltid nr och färg på det kort som finns högst upp i StockPile*/
import java.util.ArrayList;

public class StockPile extends Pile {
    // Constructor
    public StockPile(ArrayList<Card> cards, Game myGame) {
        super(cards, myGame);
    }

    // Methods
    // Ta kortet som finns högst upp i StockPilen och skicka in det till moveCard() metoden.
    public void moveTopCard() {
        moveCard(myGame.getTopCard(this));
    }
}
