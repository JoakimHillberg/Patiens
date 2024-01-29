/* En subclass till Pile med en ArrayList där alla kort som inte placeras ut på planen startar.
   Det är Deck som spelaren drar kort ifrån som placeras i handen.
   Spelaren kan inte se några av de kort som finns i Deck.*/
import java.util.ArrayList;

public class Deck extends Pile {
    // Constructor
    public Deck(ArrayList<Card> cards, Game myGame) {
        super(cards, myGame);
    }

    // Methods
    // Metod som tar ett kort ur Deck(drahögen) och placerar det i Stockpile(handen).
    // Om Deck är tomt tas alla kort från StockPile och placeras tillbaka i Deck i samma ordning som tidigare.
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
