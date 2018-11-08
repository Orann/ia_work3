package work3;

/**
 *
 * @author Claire, Esther & Orann
 */
public enum CellState {
    CLOUD("Cloud"),
    RAINBOW_POOP("Rainbow poop"),
    PORTAL("Portal"),
    HOLE("Hole"),
    PINK_MONSTER("Pink monster"),
    EMPTY("Empty"),
    UNKNOWN("Unknown");
    

    private String name = "";

    CellState(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }    
}
