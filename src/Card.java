/* A Card that has a number(1-13) and a color(♢, ♡, ♣ eller ♠)
   Card also has a boolean that says if the player should be able to see the card or not.
   The value of the boolean depends on what Pile the Card is in. */
public class Card {
    // Attributes
    private int nr;
    private String color;
    private boolean hidden;

    // Constructor
    public Card(int nr, String color, boolean hidden) {
        this.nr = nr;
        this.color = color;
        this.hidden = hidden;
    }

    // Getter and Setters
    public int getNr() {
        return nr;
    }

    public String getColor() {
        return color;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
