package work3;

import java.util.ArrayList;

/**
 *
 * @author Claire, Esther & Orann
 */
public class Agent {
    private Sensor sensor;
    private Effector effector;
    private Action intention;
    private ArrayList<ArrayList<CellState>> beliefs;
    private Position position;

    public Agent(Sensor sensor, Effector effector) {
        this.sensor = sensor;
        this.effector = effector;
    }
    
    public boolean desire(){
        
        return true;
    }    
    
    public void update(){
        
    }
    
    public Action inferenceMotor(){
        
        return Action.SHOOT;
    }
    
    public void act(){
        
    }
    
    public void reinitialisate(){
        
    }
}
