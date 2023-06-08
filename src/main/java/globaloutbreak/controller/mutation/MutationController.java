package globaloutbreak.controller.mutation;

import globaloutbreak.model.mutation.Mutation;

/**
 * interface mutation controller.
 */
public interface MutationController {


    /**
     * Dysplay the name.
     * 
     */
    void displayMutationsName();

     /**
     * Display the description.
     * 
     */
    void displayMutationsDesc(String name);

    /**
     * do the increment of the mutation.
     * 
     * @param mutation mutation
     */
    void update(String name);

}
