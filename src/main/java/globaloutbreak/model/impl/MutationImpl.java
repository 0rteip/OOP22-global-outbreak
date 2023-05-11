package globaloutbreak.model.impl;

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
        return cost;
    }

    @Override
    public int getIncrease() {
       return increase;
    }

    @Override
    public String getName() {
       return name;
    }

    @Override
    public TypeMutation getType() {
      return type;
    }

    @Override
    public String getDescription() {
       return description;
    }

    @Override
    public void increase() {
       switch(type){
        case TRASMISSION: 
            //funzione T
            break;
        case SYMPTOMS: 
            //funzione S
            break;
        case HEATRESISTANCE: 
            //funzione CAL
            break;
        case COLDRESISTANCE: 
            //funzione FRED
            break;
        case DRUGRESISTANCE: 
            //funzione FARM
            break;
        default:
       }

    }
    
}
