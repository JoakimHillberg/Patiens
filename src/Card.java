/* Ett Card som har en siffra(1-13) och en färg(♢, ♡, ♣ eller ♠)
   Card har även en boolean som berättar om användaren borde kunna se kortet eller inte.
   Värdet på boolean beror på vilken hög kortet befinner sig i. */
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
