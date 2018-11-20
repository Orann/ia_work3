package work3;

import java.util.ArrayList;

/**
 *
 * @author Claire, Esther & Orann
 */
public class Agent {
    private Sensor sensor;
    private Effector effector;
    private Fact intention;
    private ArrayList<Fact> beliefs;
    private Position position;
    private InferenceEngine inferenceEngine;
    private int mapSize;
    
    /**
     * Constructor
     * @param sensor
     * @param effector
     * @param inferenceEngine
     * @param mapSize 
     */
    public Agent(Sensor sensor, Effector effector, InferenceEngine inferenceEngine, int mapSize) {
        this.sensor = sensor;
        this.effector = effector;
        this.inferenceEngine = inferenceEngine;
        this.position = new Position(0, 0);
        this.mapSize = mapSize;
        this.beliefs = this.inferenceEngine.getFacts();
    }
    
    //Goal to reach
    public boolean desire() {
        return false; // Game has no end
    }
    
    /**
     * Princpal function of the agent
     * Gets his intention from the inference engine
     * and executes it
     */
    public void act() {
        this.intention = inferenceEngine.getAction();
        if (intention.getState() == State.GOAL) {
            System.out.println("Goal reached ! Next Level ");
            this.effector.win();
            this.reset();
        } else if (intention.getState() == State.DEATH) {
            this.effector.die();
            this.position = new Position(0, 0);
        } else if (intention.getState() == State.THROW_ROCK) {
            System.out.println("Agent throws rock");
            this.position = intention.getPosition();
            this.effector.move(intention.getPosition());
            this.effector.tryKillMonster(intention.getPosition());
            this.inferenceEngine.removeFact(intention);
            State exploredState = this.sensor.explore(intention.getPosition());
            Fact newFact = new Fact(exploredState, intention.getParent(), intention.getPosition());
            this.inferenceEngine.addFact(newFact);
            if(exploredState != State.PINK_MONSTER && exploredState != State.HOLE){
                this.addFrontier(newFact);                
            }
            
        } else if (intention.getState() == State.EXPLORE) {
            System.out.println("Agent explores");
            this.position = intention.getPosition();
            this.effector.move(intention.getPosition());
            this.inferenceEngine.removeFact(intention);
            Fact newFact = new Fact(this.sensor.explore(intention.getPosition()), intention.getParent(), intention.getPosition());
            this.inferenceEngine.addFact(newFact);
            this.addFrontier(newFact);
            
        } else if (intention.getState() == State.CLOUD || intention.getState() == State.RAINBOW_POOP || intention.getState() == State.EMPTY || intention.getState() == State.UNKNOWN || intention.getState() == State.HOLE || intention.getState() == State.PINK_MONSTER) {
            System.out.println("Agent does nothing...");
            System.out.println("You're lost in the forest, GAME OVER : you're in state " + intention.getState());
            while (true);
        }

    }

    /**
     * When the agent is exploring a case, this function add the neighboors of it
     * as Frontier Cases in the fact base
     */
    private void addFrontier(Fact nextAction) {
        int currentX = nextAction.getPosition().getX();
        int currentY = nextAction.getPosition().getY();
        Fact f;
        if (currentX >= 0) {
            f = this.inferenceEngine.findFact(new Position(currentX - 1, currentY));
            if (f.getState() == State.UNKNOWN) {
                this.inferenceEngine.removeFact(f);
                this.inferenceEngine.addFact(new Fact(State.FRONTIER, nextAction, f.getPosition()));
            }
        }
        if (currentX < mapSize) {
            f = this.inferenceEngine.findFact(new Position(currentX + 1, currentY));
            if (f.getState() == State.UNKNOWN) {
                this.inferenceEngine.removeFact(f);
                this.inferenceEngine.addFact(new Fact(State.FRONTIER, nextAction, f.getPosition()));
            }
        }
        if (currentY >= 0) {
            f = this.inferenceEngine.findFact(new Position(currentX, currentY - 1));
            if (f.getState() == State.UNKNOWN) {
                this.inferenceEngine.removeFact(f);
                this.inferenceEngine.addFact(new Fact(State.FRONTIER, nextAction, f.getPosition()));
            }
        }
        if (currentY < mapSize) {
            f = this.inferenceEngine.findFact(new Position(currentX, currentY + 1));
            if (f.getState() == State.UNKNOWN) {
                this.inferenceEngine.removeFact(f);
                this.inferenceEngine.addFact(new Fact(State.FRONTIER, nextAction, f.getPosition()));
            }
        }
    }

    /**
     * When the agent wins, it reset the caracteristics of the agent
     */
    private void reset() {
        this.mapSize++;
        inferenceEngine.reset(mapSize);
        this.position = new Position(0, 0);
    }
}
