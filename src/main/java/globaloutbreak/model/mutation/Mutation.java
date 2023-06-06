package globaloutbreak.model.mutation;

import globaloutbreak.model.api.Disease;

/**
 * 
 */

public interface Mutation {
 
    /**
     * Gets the cost of the mutation
     * 
     * @return mutation cost
     */
    int getCost();

    /**
     * Gets the increment of the increase of the paremeter of the disease
     * 
     * @return mutation increment
     */
    int getIncrease();

    /**
     * Gets the name of the mutation
     * 
     * @return mutation name
     */
    String getName();

    /**
     * Gets the type of the mutation
     * 
     * @return mutation type
     */
    TypeMutation getType();

    /**
     * Gets the description of the mutation
     * 
     * @return mutation descriptions
     */
    String getDescription();

    /**
     * increase the parameter
     * 
     */
    void increase (Disease disease);

    /**
     * decrease the parameter
     * 
     */
    void decrease (Disease disease);
}
