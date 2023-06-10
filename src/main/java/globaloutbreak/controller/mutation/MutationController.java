package globaloutbreak.controller.mutation;

import globaloutbreak.controller.Controller;
import globaloutbreak.model.Model;

/**
 * interface mutation controller.
 */
public interface MutationController {


    /**
     * Dysplay the name.
     * 
     *@param controller  controller
     *
     */
    void displayMutationsName(Controller controller);

     /**
     * Display the description.
     * 
     * @param name mutation
     * @param controller controller
     */
    void displayMutationsDesc(String name, Controller controller);

    /**
     * do the increment of the mutation.
     * 
     * @param name mutation
     *  @param model mutation           
     */
    void update(String name, Model model);

}
