package globaloutbreak.controller.api;


import java.util.List;

import globaloutbreak.model.api.Mutation;

public interface MutationController {


    /**
     * load mutation 
     * @param path name of the mutation
     */
    public void loadMutationFromFile(final String path);

    /**
     * 
     * @return loist oh the mutation
     */
    public List<Mutation> getMutation();
    
    /**
     * do the increment of the mutation
     * @param mutation mutation
     */
    public void increment(Mutation mutation);

  
}
