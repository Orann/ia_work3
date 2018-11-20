package work3;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Claire, Esther & Orann
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Environment e = new Environment();
        InferenceEngine i = new InferenceEngine(e.getMapSize());
        Sensor sensor = new Sensor(e);
        Effector effector = new Effector(e);
        Agent agent = new Agent(sensor, effector, i, e.getMapSize());        
        
        while(!agent.desire()){
            System.out.print(e);
            agent.act();   
            System.out.println("");
            TimeUnit.SECONDS.sleep(1);
        }        
    }
    
}
