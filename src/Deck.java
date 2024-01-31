/* A subclass for Pile with an ArrayList where all cards are generated and end up at the start if they are not on the board..
   Deck is where the player pulls Cards from that end up in the StockPile(hand).
   The player cannot see any of the cards that are in the Deck. */
import java.util.ArrayList;

public class Deck extends Pile {
    // Constructor
    public Deck(ArrayList<Card> cards, Game myGame) {
        super(cards, myGame);
    }

    // Methods
    // Method that takes a Card from Deck and places it in Stockpile(the hand).
    // If Deck is empty all Cards are taken from StockPile and placed back in Deck in the same order as before.
    public void drawCard() {
        if (this.cards.isEmpty()) {
            int size = myGame.getMyStockPile().cards.size();
            for (int i = 0; i < size; i++) {
                myGame.getTopCard(myGame.getMyStockPile()).setHidden(true);
                this.cards.add(myGame.getTopCard(myGame.getMyStockPile()));
                myGame.getMyStockPile().cards.remove(myGame.getTopCard(myGame.getMyStockPile()));
            }
        }

        Card topCard = myGame.getTopCard(this);
        topCard.setHidden(false);
        myGame.getMyStockPile().cards.add(topCard);
        this.cards.remove(topCard);
    }
}
