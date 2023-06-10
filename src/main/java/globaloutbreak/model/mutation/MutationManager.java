package globaloutbreak.model.mutation;

import java.util.Set;

/**
 * interface of muatation  manager.
 */
public interface MutationManager {

    /**
     * add mutation to active.
     * @param mutationName the name of the mutation
     */
    void addToActivate(String mutationName);

    /**
     * remuve mutation to active.
     * @param mutationName name of the mutation
     */
    void removeToActivate(String mutationName);

    /**
     * check if is active.
     * @param mutationName the name og the mutation
     * @return true or false
     */
    boolean isActivate(String mutationName);

    /**
     * recive the active mutation.
     * @return list of active mutation
     */
    Set<String> getActivateMutation();
}
