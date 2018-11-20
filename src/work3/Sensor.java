package work3;

/**
 *
 * @author Claire, Esther & Orann
 */
public class Sensor {
    private Environment environment;

    /**
     * Constructor
     * @param environment 
     */
    public Sensor(Environment environment) {
        this.environment = environment;
    }
    
    /**
     * Explores a position to get its state
     * @param position
     * @return 
     */
    public State explore(Position position) {
        State state = this.environment.getCellForest(position);
        return state;
    }
}
