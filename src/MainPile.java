/* En subclass till Pile där alla kort slumpas ut i början av spelet(sju stycken).
   I början ser spelaren bara det kortet som finns högst upp.
   Under spelet ser spelaren även de kort som de flyttat mellan högarna. */
import java.util.ArrayList;

public class MainPile extends Pile {
    // Constructor
    public MainPile(ArrayList<Card> cards, Game myGame) {
        super(cards, myGame);
    }

    // Methods
    // Metod som frågar användaren hur många kort de vill flytta från denna MainPile.
    // Tar kortet på botten av de användaren vill flytta och skickar in  det i moveCard() metoden.
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
