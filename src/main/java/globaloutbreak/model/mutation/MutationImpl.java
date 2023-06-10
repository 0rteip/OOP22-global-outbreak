package globaloutbreak.model.mutation;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
//import globaloutbreak.model.ModelImpl;
import globaloutbreak.model.disease.Disease;
/**
 * class mutation impl.
 */
public final class MutationImpl implements Mutation { 

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String name;
    private final int cost;
    private final float increase;
    private final TypeMutation type;
    private final String description;
    //private final ModelImpl modelImpl;

    /**
     * constructor.
     * @param cost cost of the mutation
     * @param name name of the mutation
     * @param increase increase of the mutation
     * @param type type of the mutation
     * @param description description of the mutation
     */
    public MutationImpl(final int cost, final String name, final float increase, final TypeMutation type, 
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
    public float getIncrease() {
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
        selectType(increase, disease);
    }
    @Override
    public void decrease(final Disease disease) {
        final float decrease = -increase;
        selectType(decrease, disease);
    }
    private void selectType(final float increment, final Disease disease) {
        switch (this.type) {
            case TRASMISSION: 
                disease.updateInfectivity(increment);
                System.out.println("A");
                break;
            case AIR: 
                disease.updateAirInfectivity(increment);
                break;
            case LAND: 
                disease.updateLandInfectivity(increment);
                break;
            case SEA: 
                disease.updateSeaInfectivity(increment);
                break;
            case SYMPTOMS: 
                disease.updateLethality(increment);
                break;
            case HEATRESISTANCE: 
                disease.updateHeatInfectivity(increment);
                break;
            case COLDRESISTANCE: 
                disease.updateColdInfectivity(increment);
                break;
            case DRUGRESISTANCE: 
                disease.updateCureResistance(increment);
                break;
            default:
                logger.warn("Type {} not found.", type);
                break;
       }
    }
}
