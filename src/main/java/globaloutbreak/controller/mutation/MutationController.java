package globaloutbreak.controller.mutation;

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
     * @param name 
     *              mutation
     */
    void displayMutationsDesc(String name);

    /**
     * do the increment of the mutation.
     * 
     * @param name 
     *              mutation
     */
    void update(String name);

}
