package globaloutbreak.model.mutation;

import java.util.*;

public class MutationData {
    
    private List<Mutation> mutations;
    private MutationFactoryImpl mutationFactory;

    public MutationData( MutationFactoryImpl mutationFactory){
        this.mutationFactory = mutationFactory;
        mutations = new ArrayList<>();
    }

    /**
     * get the mutatuions list
     * 
     * @return list of mutations
     */
    public List<Mutation> getMutations() {
        ArrayList<Mutation> defensiveCopy = new ArrayList<>(mutations);
       
        return defensiveCopy;
    }
    

   public void loadMutationFromJson(final int cost, final String name, final int increase, final TypeMutation type, final String description){
    //istanzio e aggiungo alla lista
    Mutation mutation=mutationFactory.createMutation(cost, name, increase, type, description);
    mutations.add(mutation);
   }

}