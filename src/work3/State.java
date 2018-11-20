package work3;

/**
 * Possible states for the facts and the forest's cells 
 * @author Claire, Esther & Orann
 */
public enum State {
    CLOUD("Cloud"),
    RAINBOW_POOP("Rainbow poop"),
    PORTAL("Portal"),
    HOLE("Hole"),
    PINK_MONSTER("Pink monster"),
    EMPTY("Empty"),
    UNKNOWN("Unknown"),
    THROW_ROCK("Throw rock"),
    FRONTIER("Frontier"),
    EXPLORE("Explore"),
    GOAL("Goal"),
    DEATH("Death");    

    private String name = "";

    State(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }    
}
