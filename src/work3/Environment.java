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
    
    public void updateForestLevel(){
        level++;
        forest.clear();
        generateForest();
    }
    
    private void generateForest(){
        ArrayList<CellState> line = new ArrayList<>();
        for(int i = 0 ; i < level ; i++){ //collumn
            line.add(CellState.EMPTY);
        }
        for(int i = 0 ; i < level ; i++){
            forest.add((ArrayList<CellState>)line.clone());
        }
        
        ArrayList<Position> randomAuthorized = new ArrayList<>();
        for(int i = 0 ; i < level ; i++){
            for(int j = 0 ; j < level ; j++){
                randomAuthorized.add(new Position(j, i));
            }
        }
        randomAuthorized.remove(0);        
        
        int random = (int)Math.random() * (randomAuthorized.size());
        forest.get(randomAuthorized.get(random).getX()).set((randomAuthorized.get(random).getY()), CellState.PORTAL);
        randomAuthorized.remove(randomAuthorized.get(random));
        
        int nbPinkMonster = (level/2)+(level%2);
        int nbHole = (level/2);
        
        int countPinkMonster = 0;
        int countHole = 0;
        
        Position position ;
       
        while(countPinkMonster != nbPinkMonster && countHole!= nbHole){
            random = (int)Math.random() * (randomAuthorized.size());
            position = randomAuthorized.get(random);
            if(forest.get(position.getX()).get(position.getY()) == CellState.EMPTY){
                if (countPinkMonster < nbPinkMonster){
                    addState(CellState.PINK_MONSTER, position, randomAuthorized);
                }
                else{
                    addState(CellState.HOLE, position, randomAuthorized);      
                }
            }
        }
    }
    
    private void addState(CellState state, Position p, ArrayList<Position> list){
        CellState borderState; 
        if (state == CellState.PINK_MONSTER){
            borderState = CellState.RAINBOW_POOP;
        }
        else{
            borderState = CellState.CLOUD;
        }
    }

    public int getPerformance() {
        return performance;
    }

    @Override
    public String toString() {
        return "Environment{" + "performance=" + performance + ", agentPosition=" + agentPosition + ", forest=" + forest + '}';
    }    
}
