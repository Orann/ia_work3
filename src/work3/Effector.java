package work3;

/**
 *
 * @author Claire, Esther & Orann
 */
public class Effector {
    Environment environment;

    /**
     * Constructor
     * @param environment 
     */
    public Effector(Environment environment) {
        this.environment = environment;
    }
    
    /**
     * What the agent does through its effector when it wins
     */
    public void win(){
        environment.updateForestLevel();
    }
    
    /**
     * The agent moves in the environment through its effector
     * @param nextPosition 
     */
    public void move(Position nextPosition){
        environment.updateAgentPosition(nextPosition);
        // we consider that the agent can go to any known cell with a cost of mapSize
    }
    
    /**
     * The agent tries to kill a monster through its effector
     * @param positionTarget 
     */
    public void tryKillMonster(Position positionTarget){
        environment.tryKillMonster(positionTarget);
    }

    /**
     * what happens in the environment when an agent dies
     */
    public void die() {
        environment.agentDies();
    }
    
}
