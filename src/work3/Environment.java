package work3;

import java.util.ArrayList;

/**
 *
 * @author Claire, Esther & Orann
 */
public class Environment {

    private int performance;
    private Position agentPosition;
    private ArrayList<ArrayList<CellState>> forest;
    private int level;

    public Environment() {
        level = 3;
        performance = 0;
        forest = new ArrayList<>();
        generateForest();

    }

    public void updateForestLevel() {
        level++;
        forest.clear();
        generateForest();
    }

    private void generateForest() {
        ArrayList<CellState> line = new ArrayList<>();
        for (int i = 0; i < level; i++) { //collumn
            line.add(CellState.EMPTY);
        }
        for (int i = 0; i < level; i++) {
            forest.add((ArrayList<CellState>) line.clone());
        }

        ArrayList<Position> randomAuthorized = new ArrayList<>();
        for (int i = 0; i < level; i++) {
            for (int j = 0; j < level; j++) {
                randomAuthorized.add(new Position(j, i));
            }
        }
        agentPosition = new Position(0, 0);

        int random;

        int nbPinkMonster = ((level / 2) + (level % 2)) - 1;
        int nbHole = (level / 2);

        int countPinkMonster = 0;
        int countHole = 0;

        Position position;
        int rand;
        while (countPinkMonster != nbPinkMonster || countHole != nbHole) {
            random = (int) (Math.random() * (randomAuthorized.size()));
            position = randomAuthorized.get(random);
            if (forest.get(position.getX()).get(position.getY()) == CellState.EMPTY && 
                    (position.getX() != agentPosition.getX() || 
                    position.getY() != agentPosition.getY())) {
                if (countPinkMonster < nbPinkMonster) {
                    forest.get(position.getX()).set(position.getY(), CellState.PINK_MONSTER);
                    countPinkMonster++;
                    randomAuthorized.remove(position);
                    addState(CellState.PINK_MONSTER, position, randomAuthorized, level);
                } else {
                    forest.get(position.getX()).set(position.getY(), CellState.HOLE);
                    countHole++;
                    randomAuthorized.remove(position);
                    addState(CellState.HOLE, position, randomAuthorized, level);
                }
            }
        }
        random = (int) (Math.random() * (randomAuthorized.size())) + 1;
        forest.get(randomAuthorized.get(random).getX()).set((randomAuthorized.get(random).getY()), CellState.PORTAL);
        randomAuthorized.remove(randomAuthorized.get(random));
    }

    private void addState(CellState state, Position p, ArrayList<Position> list, int level) {
        CellState borderState;
        if (state == CellState.PINK_MONSTER) {
            borderState = CellState.RAINBOW_POOP;
        } else {
            borderState = CellState.CLOUD;
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
        for (int i = 0; i < level; i++) {
            for (int j = 0; j < level; j++) {
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
                if (agentPosition.getX() == j && agentPosition.getY() == i) {
                    print = print.concat("*   ");
                }
                else{
                    print = print.concat("    ");
                }

            }
            print = print.concat("\n");
        }
        return (print);
    }
}
