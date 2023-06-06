package globaloutbreak.model.mutation;

/**
 * Interface of mutation factory
 * 
 */

public interface MutationFactory {
    /**
     * ctreate mutation by data
     * 
     * @param cost cost of the mutation
     * @param name name of the mutation
     * @param increase increas of the mutation parameter
     * @param type type of the mutation
     * @param description description of the mutation
     * @return mutation
     */
    Mutation createMutation(final int cost, final String name, final int increase, final TypeMutation type,final String description);
}
