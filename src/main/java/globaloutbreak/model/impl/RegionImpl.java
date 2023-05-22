package globaloutbreak.model.impl;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import globaloutbreak.model.api.Region;
import globaloutbreak.model.api.TransmissionMeans;

public class RegionImpl implements Region{
    private Integer numInfected = 0;
    private Integer numDead = 0;
    private Integer numCared = 0;
    private final Integer popTot;
    private final String name;
    private final Integer urban;
    private final Integer poor;
    private Integer facilities;
    private boolean hasStartedResearc;
    private Map<String,TransmissionMeans> trasmissionMeans;
    public RegionImpl(final Integer ppTot, final String name, final Integer urban, final Integer poor, final Optional<Integer> airports, final Optional<Integer> ports, final Optional<List<String>> neighboringStates) {
        this.popTot = ppTot;
        this.name = name;
        this.urban = urban;
        this.poor = poor;
        hasStartedResearc = false;
        createMeans(airports, ports, neighboringStates);
    }
    
    private void createMeans(final Optional<Integer> airports, final Optional<Integer> ports, final Optional<List<String>> neighboringStates) {
        if(airports.isPresent()) {
            for(int i = 0; i<airports.get(); i++) {
                trasmissionMeans.put("airports" + i, new TransmissionMeansImpl(Optional.empty(), Optional.of(new File("C:\\Users\\luca0\\OneDrive\\Desktop\\ProgrammiPersonali\\global-outbreak\\src\\main\\resources\\region\\airport.json")) ));
            }
        } 
        
        if(ports.isPresent()) {
            for(int i = 0; i<airports.get(); i++) {
                trasmissionMeans.put("ports " + i, new TransmissionMeansImpl(Optional.empty(), Optional.of(new File("C:\\Users\\luca0\\OneDrive\\Desktop\\ProgrammiPersonali\\global-outbreak\\src\\main\\resources\\region\\port.json"))));
            }
        } 

        if(neighboringStates.isPresent()) {
            trasmissionMeans.put("floor", new TransmissionMeansImpl(neighboringStates, Optional.empty()));
        }

    } 

    public void incDeathPeople(final Integer dead) {
        this.numDead += dead;
    }

    public void incOrDecInfectedPeople(final Integer infected) {
        this.numInfected += infected;
    }

    public void incOrDecCuredPeople(final Integer cared) {
        this.numCared += cared;
    }

    public Integer getNumInfected() {
        return numInfected;
    }
    
    public Integer getNumDead() {
        return numDead;
    }
    
    public Integer getNumCared() {
        return numCared;
    }

    public String getName() {
        return name;
    }

    public Integer getUrban() {
        return urban;
    }
    
    public Integer getPopTot() {
        return popTot;
    }

    public Integer getPoor() {
        return poor;
    }

    public Integer getFacilities() {
        return facilities;
    }
    
    public Boolean getHasStartedResearch() {
        return hasStartedResearc;
    }
    
    public void startResearch() {
        this.hasStartedResearc = true;
    }

}
