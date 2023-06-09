package globaloutbreak.model.region;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import globaloutbreak.model.cure.RegionCureStatus;
import globaloutbreak.model.pair.Pair;

/**
 * Implementation of Region.
 */
public final class RegionImpl implements Region {
    private int numInfected;
    private int numDead;
    //private final int numCared;
    private final Integer popTot;
    private final String name;
    private final float urban;
    private final float poor;
    private final Integer facilities;
    private final Integer color;
    private final ClimateInt climate;
    private RegionCureStatus status = RegionCureStatus.NONE;
    //private State statusCure;
    private final List<TransmissionMean> trasmissionMeans = new LinkedList<>();

    /**
     * This is the constructor.
     * 
     * @param popTot
     *              the total population 
     * @param name 
     *              the name of the region
     * @param reachableRegion
     *                        means with the list of reachable state and the numbre
     *                        of the mean
     * @param urban
     *                        percentage of people living in urban areas
     * @param poor
     *                        percentage of people who are below the poverty line
     * @param color
     *                        the color of the region
     * @param facilities
     *                        number of care facilities
     * @param hot
     *                        percentage of hot climate
     * @param humid
     *                        percentage of humidity
     * 
     */
    public RegionImpl(final int popTot, final String name, 
            final Map<String, Pair<Integer, Optional<List<String>>>> reachableRegion, final float urban, 
            final float poor, final int color, final int facilities, final float hot, final float humid) {
        this.popTot = popTot;
        this.name = name;
        this.urban = urban;
        this.poor = poor;
        this.color = color;
        this.facilities = facilities;
        this.climate = new Climate(humid, hot);
        createMeans(reachableRegion);
        // this.statusCure = State.NEUTRO;
    }

    private void createMeans(final Map<String, Pair<Integer, Optional<List<String>>>> reachableRegion) {
        reachableRegion.forEach((mean, list) -> {
            addMeans(list, mean);
        });
    }

    private void addMeans(final Pair<Integer, Optional<List<String>>> pair, final String means) {
        final int n = pair.getX();
        final Optional<List<String>> list = pair.getY();
        for (int i = 0; i < n; i++) {
            trasmissionMeans.add(new TransmissionMeansImpl(list, means));
        }
    }

    @Override
    public void incDeathPeople(final int dead) {
        this.numDead += dead;
    }

    @Override
    public void incOrDecInfectedPeople(final int infected) {
        this.numInfected += infected;
    }

    @Override
    public int calcPercInfected() {
        return (numInfected * 100) / popTot;
    }

    @Override
    public int getNumInfected() {
        return numInfected;
    }

    @Override
    public int getNumDeath() {
        return numDead;
    }

    /*@Override
    public int getNumCared() {
        return numCared;
    }*/

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getUrban() {
        return urban;
    }

    @Override
    public int getPopTot() {
        return popTot;
    }

    @Override
    public float getPoor() {
        return poor;
    }

    @Override
    public int getFacilities() {
        return facilities;
    }

    @Override
    public List<TransmissionMean> getTrasmissionMeans() {
        return new LinkedList<>(trasmissionMeans);
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public ClimateInt getClimate() {
        return this.climate;
    }

    @Override
    public RegionCureStatus getCureStatus() {
        return this.status;
    }

    @Override
    public void setCureStatus(final RegionCureStatus status) {
        this.status = status;
    }

}
