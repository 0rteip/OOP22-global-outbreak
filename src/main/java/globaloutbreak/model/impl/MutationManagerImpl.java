package globaloutbreak.model.impl;

import java.util.HashSet;
import java.util.Set;

import globaloutbreak.model.api.MutationManager;

public class MutationManagerImpl implements MutationManager {

    private Set<String> activateMutation;

    public MutationManagerImpl(){
       activateMutation = new HashSet<>();
    }

    @Override
    public void addToActivate(String mutationName) {
        activateMutation.add(mutationName);
    }

    @Override
    public void removeToActivate(String mutationName) {
        activateMutation.remove(mutationName);
    }

    @Override
    public boolean isActivate(String MutationName) {
       return activateMutation.contains(MutationName);
    }

    @Override
    public Set<String> getActivateMutation() {
        HashSet<String> defensiveCopy = new HashSet<>(activateMutation);

      return defensiveCopy;
    }
    
}
