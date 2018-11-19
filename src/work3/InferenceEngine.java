/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work3;

import java.util.ArrayList;

/**
 *
 * @author orann
 */
public class InferenceEngine {
    ArrayList<Fact> facts;
    ArrayList<Rule> rules;

    public InferenceEngine() {
        facts = new ArrayList<>();
        rules = new ArrayList<>();
        
        ArrayList<Fact> premises = new ArrayList<>();
        ArrayList<Fact> inferences = new ArrayList<>();
        premises.add(Fact.IS_LIGHT);
        inferences.add(Fact.HAS_WON);        
        rules.add(new Rule(premises,inferences));
        
        premises = new ArrayList<>();
        inferences = new ArrayList<>();
        premises.add(Fact.IS_WITH_MONSTER);
        inferences.add(Fact.HAS_LOST);        
        rules.add(new Rule(premises,inferences));
        
        premises = new ArrayList<>();
        inferences = new ArrayList<>();
        premises.add(Fact.IS_IN_HOLE);
        inferences.add(Fact.HAS_LOST);        
        rules.add(new Rule(premises,inferences));
        
        premises = new ArrayList<>();
        inferences = new ArrayList<>();
        premises.add(Fact.BEGINS_LEVEL);
        inferences.add(Fact.GO_RANDOM);        
        rules.add(new Rule(premises,inferences));
        
    }
    
        
    
}
