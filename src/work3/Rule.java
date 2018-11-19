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
public class Rule {

    private final ArrayList<Fact> premises;
    private final ArrayList<Fact> inferences;

    public Rule(ArrayList<Fact> premises, ArrayList<Fact> inferences) {
        this.premises = premises;
        this.inferences = inferences;
    }

    public boolean isRuleApplicable(ArrayList<Fact> currentFacts) {
        boolean ret = true;
        int count = 0;
        while (count < premises.size() && ret) {
            boolean isPremiseFound = false;
            int numFact = 0;
            while(numFact < currentFacts.size() && !isPremiseFound){
                if (currentFacts.get(numFact) == premises.get(count)) {
                    isPremiseFound = true;
                }
            }
            if(!isPremiseFound) {
                ret = false;
            }
        }
        return ret;
    }

    public ArrayList<Fact> getInferences() {
        return inferences;
    }
    
    public int getPriority(){
        return premises.size();
    }

}
