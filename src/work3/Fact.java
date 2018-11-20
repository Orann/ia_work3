package work3;

/**
 *
 * @author Claire, Esther & Orann
 */
public class Fact {
    private State state;
    private Fact parent; // a parent is the fact that, when being explored, adds the new fact to the frontier
    private Position position;  

    public Fact(State state, Fact parent, Position position) {
        this.state = state;
        this.parent = parent;
        this.position = position;
    }

    public State getState() {
        return state;
    }

    public Fact getParent() {
        return parent;
    }
    
    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return ""+state;
    }
    
    
}
