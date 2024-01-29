/* Superclass till alla olika Piles.
   Innehåller en ArrayList med de kort som högen innehåller. */
import java.util.ArrayList;
import java.util.Scanner;

public class Pile {
    // Attributes
    protected Scanner myScan = new Scanner(System.in);
    protected Game myGame;
    protected ArrayList<Card> cards;

    // Constructor
    public Pile(ArrayList<Card> cards, Game myGame) {
        this.cards = cards;
        this.myGame = myGame;
    }

    // Method
    // En metod som flyttar det kort som skickas in från denna Pile till den som spelaren väljer.
    // Om användaren flyttar flera kort är selectedCard det som är längst ned av de.
    // När ett kort flyttas ändras kortet som var under så att hidden = false.
    // Om spelaren försöker flytta ett kort där hidden = true blir det ett invalid move.
    public void moveCard(Card selectedCard) {
        myGame.printBoard();
        for (int i = 0; i < myGame.getPileList().length - 1; i++) {
            System.out.println((i + 1) + ": " + myGame.getPileList()[i]);
        }

        System.out.print("Input index of pile to move card to: ");
        int pileNr = myGame.tryParse(myScan.nextLine());

        while (pileNr < 1 || pileNr > 7) {
            System.out.print("Must input a valid index of a pile: ");
            pileNr = myGame.tryParse(myScan.nextLine());
        }

        if (moveIsValid(pileNr, selectedCard) && !selectedCard.isHidden()) {
            if (this.cards.indexOf(selectedCard) > 0) {
                this.cards.get(this.cards.indexOf(selectedCard) - 1).setHidden(false);
            }

            int selectedCardIndex = this.cards.indexOf(selectedCard);
            ArrayList<Card> selectedCards = new ArrayList<>(this.cards.subList(selectedCardIndex, this.cards.size()));
            myGame.getMainPiles().get(pileNr - 1).cards.addAll(selectedCards);
            this.cards.removeAll(selectedCards);
        }
        else {
            System.out.println("That move is not valid.");
        }
    }

    // En metod som kontrollerar om ett förflyttning är giltig.
    // Blir giltig om korten alternerar färg och kortet som flyttas är 1 lägre än det som är under.
    // Om en MainPile är tom är det även giltigt att placera en kung(nr 13) på denna MainPile.
    public boolean moveIsValid(int pileNr, Card startTopCard) {
        boolean validMove = false;

        MainPile targetPile = myGame.getMainPiles().get(pileNr - 1);
        if (targetPile.cards.isEmpty() && startTopCard.getNr() == 13) {
            validMove = true;
        }
        else if (startTopCard.getNr() == myGame.getTopCard(targetPile).getNr() - 1) {
            String targetCardColor = myGame.getTopCard(targetPile).getColor();
            if (targetCardColor.equals("♡") || targetCardColor.equals("♢")) {
                if (startTopCard.getColor().equals("♣") || startTopCard.getColor().equals("♠")) {
                    validMove = true;
                }
            }
            else {
                if (startTopCard.getColor().equals("♡") || startTopCard.getColor().equals("♢")) {
                    validMove = true;
                }
            }
        }

        return validMove;
    }

    // En metod som tar det kort som är högst upp i denna hög och försöker placera det på motsvarande DiscardPile.
    // Kortet flyttas till DiscardPile för dess specifika färg om dess nr är 1 högre än det kort som  redan är i DiscardPilen.
    // Om högen är tom behöver ett ess placeras på den.
    // Kontrollerar även om högen spelaren försöker flytta från är tom och säger om den är det.
    public void discardCard() {
        Card selectedCard = myGame.getTopCard(this);

        if (this.cards.isEmpty()) {
            System.out.println("There are no cards in that pile.");
        }

        for (int i = 0; i < myGame.getDiscardPiles().size(); i++) {
            if (myGame.getColors()[i].equals(selectedCard.getColor())) {
                boolean correctFirstCard = myGame.getDiscardPiles().get(i).cards.isEmpty() && selectedCard.getNr() == 1;
                boolean correctNextCard = (myGame.getTopCard(myGame.getDiscardPiles().get(i)).getNr() == selectedCard.getNr() - 1);

                if (correctFirstCard || correctNextCard) {
                    myGame.getDiscardPiles().get(i).cards.add(selectedCard);
                    this.cards.remove(selectedCard);
                    myGame.getTopCard(this).setHidden(false);
                }
                else {
                    System.out.println("That card cannot be discarded right now.");
                }
            }
        }
    }
}
