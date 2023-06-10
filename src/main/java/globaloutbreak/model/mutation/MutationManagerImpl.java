package globaloutbreak.model.mutation;

import java.util.HashSet;
import java.util.Set;

/**
 * class mutation manager impl.
 */
public final class MutationManagerImpl implements MutationManager {

    private final Set<String> activateMutation;

    /**
     * constructor.
     */
    public MutationManagerImpl() {
       activateMutation = new HashSet<>();
    }

    @Override
    public void addToActivate(final String mutationName) {
        activateMutation.add(mutationName);
    }

    @Override
    public void removeToActivate(final String mutationName) {
        activateMutation.remove(mutationName);
    }

    @Override
    public boolean isActivate(final String mutationName) {
       return activateMutation.contains(mutationName);
    }

    @Override
    public Set<String> getActivateMutation() {
      return new HashSet<>(activateMutation);
    }
}
