package globaloutbreak.model.impl;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import globaloutbreak.model.api.TransmissionMeans;

public class TransmissionMeansImpl implements TransmissionMeans {
    private final List<String> reachableStates;
    
    public TransmissionMeansImpl(Optional<List<String>> neighboringStates, Optional<File> file) {
        if(neighboringStates.isPresent()) {
            this.reachableStates = neighboringStates.get();
        } else {
            reachableStates = new LinkedList<>();
        }
    }
    
}
