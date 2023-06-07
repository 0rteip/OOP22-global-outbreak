package globaloutbreak.model.mutation;

import java.util.ArrayList;
import java.util.List;

/**
 * class miutation data.
 */
public class MutationData {
    private final List<Mutation> mutations;
    private final MutationFactoryImpl mutationFactory;

    /**
     * constructor.
     * @param mutationFactory
     */
    public MutationData(final MutationFactoryImpl mutationFactory) {
        this.mutationFactory = mutationFactory;
        mutations = new ArrayList<>();
    }

    /**
     * get the mutatuions list.
     * 
     * @return list of mutations
     */
    public List<Mutation> getMutations() {
        return new ArrayList<>(mutations);
    }

    /**
     * load mutation from json.
     * @param cost cost description of the mutation
     * @param name name description of the mutation
     * @param increase invrease description of the mutation
     * @param type type description of the mutation
     * @param description description of the mutation
     */
   public void loadMutationFromJson(final int cost, final String name, final int increase, final TypeMutation type, 
                    final String description) {
    //istanzio e aggiungo alla lista
    final Mutation mutation = mutationFactory.createMutation(cost, name, increase, type, description);
    mutations.add(mutation);
   }
}
