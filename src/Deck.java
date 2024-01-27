import java.util.ArrayList;

public class Deck extends Pile {
    // Constructor
    public Deck(ArrayList<Card> cards, Game myGame) {
        super(cards, myGame);
    }

    // Methods
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
