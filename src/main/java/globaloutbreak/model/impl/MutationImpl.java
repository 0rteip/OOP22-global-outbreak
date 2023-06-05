package globaloutbreak.model.impl;

import globaloutbreak.model.api.Disease;
import globaloutbreak.model.api.Mutation;

public class MutationImpl implements Mutation{

    private String name;

    private int cost;

    private int increase;

    private TypeMutation type;

    private String description;

    public MutationImpl (final int cost, final String name, final int increase, final TypeMutation type, final String description ){
        this.cost = cost;
        this.name = name;
        this.increase = increase;
        this.type = type;
        this.description = description;

    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public int getIncrease() {
        return this.increase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public TypeMutation getType() {
        return this.type;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void increase(Disease disease) {
        selectType(disease,Operation.ADD);
    }
    public void decrease(Disease disease) {
        selectType(disease,Operation.SUBTRACT);

    }
    private void selectType(Disease disease, Operation op){
        switch(this.type){
            case TRASMISSION: 
                //function increase trasmission
                break;
            case SYMPTOMS: 
                //function increase symptoms
                break;
            case HEATRESISTANCE: 
                //function increase heatresistence
                break;
            case COLDRESISTANCE: 
                //function increase coldresistence
                break;
            case DRUGRESISTANCE: 
                //function increase drugresistence
                break;
            default://exception
       }

    }
    private enum Operation {
        ADD("+"),
        SUBTRACT("-");
    
        private final String symbol;
            
        Operation(String symbol) {
            this.symbol = symbol;
        }
    
        public String getSymbol() {
            return symbol;
        }
       }
}
