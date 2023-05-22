package globaloutbreak.model.impl;

import java.util.*;


import globaloutbreak.model.api.Mutation;

public class MutationData {
    
    private List<Mutation> mutations;
    private MutationFactoryImpl mutationFactory;

    public MutationData( MutationFactoryImpl mutationFactory){
        this.mutationFactory = mutationFactory;
    }

    /**
     * get the mutatuions list
     * 
     * @return list of mutations
     */
    public List<Mutation> getMutations() {
        return mutations;//copia difensiva a mettere
    }
    

   public void loadMutationFromJson(String filePath){

    mutations = new ArrayList<>();

    //leggo il json con un ciclo
    //istanzio e aggiungo alla lista
    Mutation mutation=mutationFactory.createMutation(0, "", 0, null, "");
    mutations.add(mutation);
   }

}