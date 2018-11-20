package work3;

import java.util.ArrayList;

/**
 *
 * @author Claire, Esther & Orann
 */
public class Rule {

    private final ArrayList<State> premises;
    private final State inference;
    private final int priority; //0, 1, 2, 3 or 4

    public Rule(ArrayList<State> premises, State inference, int priority) {
        this.premises = premises;
        this.inference = inference;
        this.priority = priority;
    }

    /**
     * tests if the rule is applicable on the facts' database
     * @param factsBase
     * @return 
     */
    public Fact isRuleApplicable(ArrayList<Fact> factsBase) {
        int numFact = 0;
        ArrayList<Fact> facts = new ArrayList<>();
        ArrayList<Fact> applicableFacts = new ArrayList<>();
        // tests the correspondance of the first premise
        while (numFact < factsBase.size()) {
            if (factsBase.get(numFact).getState() == premises.get(0)) {
                facts.add(factsBase.get(numFact));
            }
            numFact++;
        }
        numFact = 0;
        if (facts.isEmpty()) {
            return null;
        } 
        
        //if the rule has two premises, tests the correspondance of the second
        else if (this.premises.size() == 2) {
            if (facts.get(numFact).getParent() != null) { // if the fact has no parent, it can't match the second premise
                while (numFact < facts.size()) {

                    if (facts.get(numFact).getParent().getState() == premises.get(1)) {
                        applicableFacts.add(facts.get(numFact));
                    }
                    numFact++;
                }
                if (applicableFacts.isEmpty()) {
                    return null;
                } 
                else {
                    return applicableFacts.get(0);
                }
            } 
            else {
                return null;
            }
        } 
        
        else {
            return facts.get(0);
        }

    }

    
    public Fact getInference(ArrayList<Fact> facts) {
        return new Fact(this.inference, facts.get(0), facts.get(0).getPosition());
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Rule{" + "premises=" + premises + ", inference=" + inference + ", priority=" + priority + "}\n";
    }

}
