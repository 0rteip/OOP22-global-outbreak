package globaloutbreak.model.region;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import globaloutbreak.model.pair.Pair;
/**
 * Implementation of Region.
 */
public final class RegionImpl implements Region {
    private Integer numInfected = 0;
    private Integer numDead = 0;
    private Integer numCared = 0;
    private final Integer popTot;
    private final String name;
    private final float urban;
    private final float poor;
    private final Integer facilities;
    private final Integer color;
    private final ClimateInt climate;
    //private State statusCure;
    private List<TransmissionMeansImpl> trasmissionMeans;

    /**
     * This is the constructor
     * 
     * @param popTot 
     *              the total population 
     * @param name  
     *              the name of the region
     * @param reachableRegion
     *                        means with the list of reachable state and the numbre of the mean
     * @param urban
     *              percentage of people living in urban areas
     * @param poor
     *              percentage of people who are below the poverty line
     * @param color
     *              the color of the region
     * @param facilities
     *                  number of care facilities
     * @param hot
     *               percentage of hot climate
     * @param humid
     *              percentage of humidity
     * 
     */
    public RegionImpl(final Integer popTot, final String name, final Map<String,Pair<Integer, Optional<List<String>>>> reachableRegion, 
        final float urban, final float poor, 
        final Integer color, final Integer facilities, final float hot, final float humid) {
        this.popTot = popTot;
        this.name = name;
        this.urban = urban;
        this.poor = poor;
        this.color = color;
        this.facilities = facilities;
        this.climate = new Climate(humid, hot);
        createMeans(reachableRegion);
        //this.statusCure = State.NEUTRO;
    }

   
    private void createMeans(final Map<String,Pair<Integer,Optional<List<String>>>> reachableRegion) {
        reachableRegion.forEach((mean, list) -> {
            addMeans(list, mean);
        });
    } 

    private void addMeans(final Pair<Integer, Optional<List<String>>> pair, final String means) {
        int n = pair.getX();
        Optional<List<String>> list = pair.getY();
        for ( int i = 0; i < n; i++) {
            trasmissionMeans.add(new TransmissionMeansImpl(list, means));
        }
    }

    @Override
    public void incDeathPeople(final Integer dead) {
        this.numDead += dead;
    }

    @Override
    public void incOrDecInfectedPeople(final Integer infected) {
        this.numInfected += infected;
    }

    @Override
    public Integer calcPercInfected() {
        return (numInfected * 100) / popTot;
    }

    @Override
    public Integer getNumInfected() {
        return numInfected;
    }

    @Override
    public Integer getNumDead() {
        return numDead;
    }

    @Override
    public Integer getNumCared() {
        return numCared;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getUrban() {
        return urban;
    }

    @Override
    public Integer getPopTot() {
        return popTot;
    }

    @Override
    public float getPoor() {
        return poor;
    }

    @Override
    public Integer getFacilities() {
        return facilities;
    }

    @Override
    public List<TransmissionMeansImpl> getTrasmissionMeans() {
        return new LinkedList<>(trasmissionMeans);
    }

    @Override
    public Integer getColor() {
        return color;
    }

    @Override
    public ClimateInt getClimate() {
        return this.climate;
    }

}
