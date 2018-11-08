package work3;

/**
 *
 * @author Claire, Esther & Orann
 */
public enum Action {
    UP("Up"),
    DOWN("Down"),
    LEFT("Left"),
    RIGHT("Right"),
    SHOOT("Shoot"),
    GET_OUT("Get out");
    private String name = "";

    Action(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }    
}
