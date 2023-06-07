package globaloutbreak.model.mutation;

/**
 * class mutation factory.
 */
public final class MutationFactoryImpl implements MutationFactory {

    @Override
    public Mutation createMutation(final int cost, final String name, final int increase, final TypeMutation type, 
                                    final String description) { 
        return new MutationImpl(cost, name, increase, type, description);
    }
}
