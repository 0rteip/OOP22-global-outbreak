package globaloutbreak.model.api;

/**
 * 
 */

public interface Mutation {
 
    /**
     * Gets the cost of the mutation
     * 
     * @return mutation' cost
     */
    int getCost();

    /**
     * Gets the increment of the increase of the paremeter of the disease
     * 
     * @return mutation' increment
     */
    int getIncrement();
}
