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
    private InferenceEngine inferenceEngine;

    public Agent(Sensor sensor, Effector effector, InferenceEngine inferenceEngine) {
        this.sensor = sensor;
        this.effector = effector;
        this.inferenceEngine = inferenceEngine;
    }
    
    public boolean desire(){
        
        return true;
    }    
    
    public void update(){
        
    }
    
    public void act(){
        
    }
    
    public void reinitialisate(){
        
    }
}
