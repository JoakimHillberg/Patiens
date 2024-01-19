import java.util.ArrayList;

public class Deck extends Pile {
    // Constructor
    public Deck(ArrayList<Card> cards, Game myGame) {
        super(cards, myGame);
    }

    @Override
    public void moveCard() {
        Card topCard = this.cards.get(this.cards.size() - 1);
        myGame.getMyDrawPile().cards.add(topCard);
        this.cards.remove(topCard);

        if (this.cards.size() == 0) {
            for (int i = 0; i < myGame.getMyDrawPile().cards.size(); i++) {
                this.cards.add(myGame.getMyDrawPile().cards.get(0));
                myGame.getMyDrawPile().cards.remove(0);
            }
        }
    }
}
