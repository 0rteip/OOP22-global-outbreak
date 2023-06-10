package globaloutbreak.model.mutation;

import globaloutbreak.model.disease.Disease;

/**
 * interface mutation.
 */
public interface Mutation {
 
    /**
     * Gets the cost of the mutation.
     * 
     * @return mutation cost
     */
    int getCost();

    /**
     * Gets the increment of the increase of the paremeter of the disease.
     * 
     * @return mutation increment
     */
    float getIncrease();

    /**
     * Gets the name of the mutation.
     * 
     * @return mutation name
     */
    String getName();

    /**
     * Gets the type of the mutation.
     * 
     * @return mutation type
     */
    TypeMutation getType();

    /**
     * Gets the description of the mutation.
     * 
     * @return mutation descriptions
     */
    String getDescription();

    /**
     * increase the parameter.
     * @param disease
     */
    void increase(Disease disease);

    /**
     * decrease the parameter.
     * @param disease
     */
    void decrease(Disease disease);
}
