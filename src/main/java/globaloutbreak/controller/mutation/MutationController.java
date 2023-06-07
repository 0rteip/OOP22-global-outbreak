package globaloutbreak.controller.mutation;

import globaloutbreak.model.mutation.Mutation;

/**
 * interface mutation controller.
 */
public interface MutationController {

    /**
     * load mutation.
     * @param path name of the mutation
     */
    void loadMutationFromFile(String path);

    /**
     * Dysplay the name.
     * 
     */
    void displayMutationsName();

     /**
     * Display the description.
     * 
     */
    void displayMutationsDesc();

    /**
     * do the increment of the mutation.
     * 
     * @param mutation mutation
     */
    void update(Mutation mutation);

}
