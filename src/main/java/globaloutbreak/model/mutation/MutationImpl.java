package globaloutbreak.model.mutation;

import globaloutbreak.model.api.Disease;
/**
 * class mutation impl.
 */
public final class MutationImpl implements Mutation { 

    private String name;

    private int cost;

    private int increase;

    private TypeMutation type;

    private String description;

    /**
     * constructor.
     * @param cost cost of the mutation
     * @param name name of the mutation
     * @param increase increase of the mutation
     * @param type type of the mutation
     * @param description description of the mutation
     */
    public MutationImpl(final int cost, final String name, final int increase, final TypeMutation type, 
                        final String description) {
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
    public void increase(final Disease disease) {
        selectType(disease, Operation.ADD);
    }
    @Override
    public void decrease(final Disease disease) {
        selectType(disease, Operation.SUBTRACT);
    }
    private void selectType(final Disease disease, final Operation op) {
        switch (this.type) {
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
        Operation(final String symbol) {
            this.symbol = symbol;
        }
        public String getSymbol() {
            return symbol;
        }
       }
}
