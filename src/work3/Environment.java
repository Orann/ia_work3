package work3;

import java.util.ArrayList;

/**
 *
 * @author Claire, Esther & Orann
 */
public class Environment {

    private int performance;
    private Position agentPosition;
    private ArrayList<ArrayList<State>> forest;
    private int mapSize;

    /**
     * Constructor
     */
    public Environment() {
        mapSize = 3;
        performance = 0;
        forest = new ArrayList<>();
        generateForest();

    }

    public int getMapSize() {
        return mapSize;
    }
    
    /**
     * Update the forest when a new level is reached
     */
    public void updateForestLevel() {
        System.out.println("Agent performance : "+performance);
        this.performance += mapSize * mapSize * 10;
        mapSize++;
        forest.clear();
        agentPosition = new Position(0, 0);
        generateForest();

    }
    
    /**
     * Random generation of the forest
     */
    private void generateForest() {
        ArrayList<State> line = new ArrayList<>();
        for (int i = 0; i < mapSize; i++) { //collumn
            line.add(State.EMPTY);
        }
        for (int i = 0; i < mapSize; i++) {
            forest.add((ArrayList<State>) line.clone());
        }

        ArrayList<Position> randomAuthorized = new ArrayList<>();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                randomAuthorized.add(new Position(j, i));
            }
        }
        agentPosition = new Position(0, 0);

        int random;

        int nbPinkMonster = ((mapSize / 2) + (mapSize % 2)) - 1;
        int nbHole = (mapSize / 2);

        int countPinkMonster = 0;
        int countHole = 0;

        Position position;
        int rand;
        while (countPinkMonster != nbPinkMonster || countHole != nbHole) {
            random = (int) (Math.random() * (randomAuthorized.size()));
            position = randomAuthorized.get(random);
            if (forest.get(position.getX()).get(position.getY()) == State.EMPTY
                    && (position.getX() != agentPosition.getX()
                    || position.getY() != agentPosition.getY())) {
                if (countPinkMonster < nbPinkMonster) {
                    forest.get(position.getX()).set(position.getY(), State.PINK_MONSTER);
                    countPinkMonster++;
                    randomAuthorized.remove(position);
                    addState(State.PINK_MONSTER, position, randomAuthorized, mapSize);
                } else {
                    forest.get(position.getX()).set(position.getY(), State.HOLE);
                    countHole++;
                    randomAuthorized.remove(position);
                    addState(State.HOLE, position, randomAuthorized, mapSize);
                }
            }
        }
        random = (int) (Math.random() * (randomAuthorized.size()));
        forest.get(randomAuthorized.get(random).getX()).set((randomAuthorized.get(random).getY()), State.PORTAL);
        randomAuthorized.remove(randomAuthorized.get(random));
    }

    /**
     * Add warnings around danger 
     * part of the generation process
     * @param state
     * @param p
     * @param list
     * @param level 
     */
    private void addState(State state, Position p, ArrayList<Position> list, int level) {
        State borderState;
        if (state == State.PINK_MONSTER) {
            borderState = State.RAINBOW_POOP;
        } else {
            borderState = State.CLOUD;
        }
        ArrayList<Position> toRemove = new ArrayList<>();
        for (Position curPos : list) {
            if ((curPos.getX() == p.getX() - 1) && (curPos.getY() == p.getY())) {
                forest.get(p.getX() - 1).set(p.getY(), borderState);
                toRemove.add(curPos);
            }
            if ((curPos.getX() == p.getX()) && (curPos.getY() == p.getY() - 1)) {
                forest.get(p.getX()).set(p.getY() - 1, borderState);
                toRemove.add(curPos);
            }
            if ((curPos.getX() == p.getX() + 1) && (curPos.getY() == p.getY())) {
                forest.get(p.getX() + 1).set(p.getY(), borderState);
                toRemove.add(curPos);
            }
            if ((curPos.getX() == p.getX()) && (curPos.getY() == p.getY() + 1)) {
                forest.get(p.getX()).set(p.getY() + 1, borderState);
                toRemove.add(curPos);
            }
        }
        for (Position currRemove : toRemove) {
            list.remove(currRemove);
        }
    }

    public int getPerformance() {
        return performance;
    }

    @Override
    public String toString() {
        String print = new String();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                switch (forest.get(i).get(j)) {
                    case CLOUD:
                        print = print.concat("C ");
                        break;
                    case RAINBOW_POOP:
                        print = print.concat("RP");
                        break;
                    case PORTAL:
                        print = print.concat("P ");
                        break;
                    case HOLE:
                        print = print.concat("H ");
                        break;
                    case PINK_MONSTER:
                        print = print.concat("PM");
                        break;
                    case EMPTY:
                        print = print.concat("E ");
                        break;
                    default:
                        print = print.concat("E ");
                }
                if (agentPosition.getX() == i && agentPosition.getY() == j) {
                    print = print.concat("*   ");
                } else {
                    print = print.concat("    ");
                }

            }
            print = print.concat("\n");
        }
        return (print);
    }

    /**
     * Update the agent position in the forest
     * @param nextPosition 
     */
    public void updateAgentPosition(Position nextPosition) {
        this.agentPosition = nextPosition;
        this.performance -= mapSize;
        
    }

    /**
     * If the position target contains a monster, it kills him
     * @param positionTarget 
     */
    public void tryKillMonster(Position positionTarget) {
        this.performance -= 10;
        if (this.forest.get(positionTarget.getX()).get(positionTarget.getY()) == State.PINK_MONSTER) {
            this.forest.get(positionTarget.getX()).set(positionTarget.getY(), State.EMPTY);
        }
    }

    /**
     * When  the agent dies he goes back to its initial position
     */
    public void agentDies() {
        this.agentPosition = new Position(0, 0);
        System.out.println("\nYou're dead");
        System.out.println("Agent performance : "+performance);
        System.out.println(this);
    }

    public State getCellForest(Position position) {
        return forest.get(position.getX()).get(position.getY());
    }
}
