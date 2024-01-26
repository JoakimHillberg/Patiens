import java.util.ArrayList;

public class Deck extends Pile {
    // Constructor
    public Deck(ArrayList<Card> cards, Game myGame) {
        super(cards, myGame);
    }

    // Methods
    @Override
    public void moveCard() {
        Card topCard = myGame.getTopCard(this);
        myGame.getMyStockPile().cards.add(topCard);
        this.cards.remove(topCard);

        if (this.cards.size() == 0) {
            for (int i = 0; i < myGame.getMyStockPile().cards.size(); i++) {
                this.cards.add(myGame.getMyStockPile().cards.get(0));
                myGame.getMyStockPile().cards.remove(0);
            }
        }
    }
}
