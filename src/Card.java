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
