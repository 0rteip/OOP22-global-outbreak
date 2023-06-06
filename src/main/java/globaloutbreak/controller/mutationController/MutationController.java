package globaloutbreak.controller.api;




import globaloutbreak.model.api.Mutation;

public interface MutationController {


    /**
     * load mutation 
     * @param path name of the mutation
     */
    public void loadMutationFromFile(final String path);

    /**
     * 
     * 
     */
    public void getMutationsName();

     /**
     * 
     * 
     */
    public void getMutationsDesc();
    
    /**
     * do the increment of the mutation
     * @param mutation mutation
     */
    public void update(Mutation mutation);

  
}
