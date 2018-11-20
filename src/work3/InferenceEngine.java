package work3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Claire, Esther & Orann
 */
public class InferenceEngine {

    private ArrayList<Fact> facts;
    private ArrayList<Rule> rules;
    private Position agentPosition;

    public InferenceEngine(int mapSize) {
        facts = new ArrayList<>();
        rules = new ArrayList<>();
        agentPosition = new Position(0, 0);

        // Initisalisation of the facts database 
        // WILL BE MODIFIED
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (i == 0 && j == 0) {
                    facts.add(new Fact(State.FRONTIER, null, new Position(i, j)));
                } else {
                    facts.add(new Fact(State.UNKNOWN, null, new Position(i, j)));
                }
            }
        }

        //Initialisation of the rules database
        // WON'T BE MODIFIED
        ArrayList<State> premises = new ArrayList<>();
        premises.add(State.PINK_MONSTER);
        rules.add(new Rule(premises, State.DEATH, 4));
        
        premises = new ArrayList<>();
        premises.add(State.HOLE);
        rules.add(new Rule(premises, State.DEATH, 4));
        
        premises = new ArrayList<>();
        premises.add(State.PORTAL);
        rules.add(new Rule(premises, State.GOAL, 4));
        
        premises = new ArrayList<>();
        premises.add(State.FRONTIER);
        rules.add(new Rule(premises, State.EXPLORE, 1));
        
        premises = new ArrayList<>();
        premises.add(State.FRONTIER);
        premises.add(State.EMPTY);
        rules.add(new Rule(premises, State.EXPLORE, 3));
        
        premises = new ArrayList<>();
        premises.add(State.FRONTIER);
        premises.add(State.RAINBOW_POOP);
        rules.add(new Rule(premises, State.THROW_ROCK, 2));
        
        premises = new ArrayList<>();
        premises.add(State.FRONTIER);
        premises.add(State.CLOUD);
        rules.add(new Rule(premises, State.EXPLORE, 1));
        
        premises = new ArrayList<>();
        premises.add(State.EMPTY);
        rules.add(new Rule(premises, State.EMPTY, 0));
        
        premises = new ArrayList<>();
        premises.add(State.RAINBOW_POOP);
        rules.add(new Rule(premises, State.RAINBOW_POOP, 0));
        
        premises = new ArrayList<>();
        premises.add(State.PINK_MONSTER);
        rules.add(new Rule(premises, State.PINK_MONSTER, 0));
        
        premises = new ArrayList<>();
        premises.add(State.HOLE);
        rules.add(new Rule(premises, State.HOLE, 0));
        
        premises = new ArrayList<>();
        premises.add(State.CLOUD);
        rules.add(new Rule(premises, State.CLOUD, 0));
        
        premises = new ArrayList<>();
        premises.add(State.UNKNOWN);
        rules.add(new Rule(premises, State.UNKNOWN, 0));  
        
        premises = new ArrayList<>();
        premises.add(State.DEATH);
        rules.add(new Rule(premises, State.DEATH, 0)); 
    }
    
    /**
     * resets the facts' database and the agent's position 
     * used when the agent dies
     * @param mapSize 
     */
    public void reset(int mapSize){
        facts = new ArrayList<>();
        agentPosition = new Position(0, 0);

        // Initisalisation of the facts database 
        // WILL BE MODIFIED
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (i == 0 && j == 0) {
                    facts.add(new Fact(State.FRONTIER, null, new Position(i, j)));
                } else {
                    facts.add(new Fact(State.UNKNOWN, null, new Position(i, j)));
                }
            }
        }
        
    }

    public void setAgentPosition(Position agentPosition) {
        this.agentPosition = agentPosition;
    }
    
    /**
     * gives the fact corresponding to the action to do
     * the action to do is determined by the best applicable rule (highest priority)
     * main function of the inference engine
     * @return 
     */
    public Fact getAction(){       
        HashMap<Rule,Fact> applicableRules = new HashMap<>();
        
        for(Rule rule : rules){
            Fact applicableFact = rule.isRuleApplicable(facts);
            if(applicableFact != null){
                applicableRules.put(rule, applicableFact);
            }
        }
        
        int maxPriority = -1;
        Rule bestRule = null;
        Fact bestApplicableFact = null;
        for(Map.Entry<Rule, Fact> couple : applicableRules.entrySet()) {
            Rule key = couple.getKey();
            Fact value = couple.getValue();
            
            if(key.getPriority() > maxPriority){
                maxPriority = key.getPriority();
                bestRule = key;
                bestApplicableFact = value;                
            }
        }
        
        ArrayList<Fact> premises = new ArrayList<>();
        premises.add(bestApplicableFact);
        premises.add(bestApplicableFact.getParent());
        facts.remove(bestApplicableFact);        
        Fact nextAction = bestRule.getInference(premises);        
        facts.add(nextAction);
        return nextAction;        
    }
    
    /**
     * finds a fact in the database thanks to its position
     * @param position
     * @return 
     */
    public Fact findFact(Position position){
        boolean foundFact = false;
        int i = 0;
        while(i<facts.size() && !foundFact){
            if(position.getX() == facts.get(i).getPosition().getX() && position.getY() == facts.get(i).getPosition().getY()){
                foundFact = true;                
            }
            i++;
        }
        return facts.get(i-1);
    }
    
    /**
     * removes a fact fom the database
     * @param factToRemove 
     */
    public void removeFact(Fact factToRemove){
        facts.remove(factToRemove);
    }
    
    /**
     * adds a fact to the database
     * @param fact 
     */
    public void addFact(Fact fact){
        facts.add(fact);
    }
    
    public ArrayList<Fact> getFacts(){
        return this.facts;
    }
}
