package globaloutbreak.model.api;

import java.util.Set;

public interface MutationManager {
    
    public void addToActivate(String mutationName);

    public void removeToActivate(String mutationName);

    public boolean isActivate(String MutationName);

    public Set<String> getActivateMutation();
}
