package globaloutbreak.model.region;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.source.doctree.SystemPropertyTree;

import globaloutbreak.model.cure.RegionCureStatus;
import globaloutbreak.model.pair.Pair;

/**
 * Implementation of Region.
 */
public final class RegionImpl implements Region {
    private int numInfected;
    private int numDeath;
    // private final int numCared;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Integer popTot;
    private final String name;
    private final float urban;
    private final float poor;
    private final Integer facilities;
    private final Integer color;
    private final Climate climate;
    private RegionCureStatus status = RegionCureStatus.NONE;
    // private State statusCure;
    private final List<TransmissionMean> trasmissionMeans = new LinkedList<>();
    private final PropertyChangeSupport infodataSupport = new PropertyChangeSupport(this);
    private final PropertyChangeSupport infodataSupport = new PropertyChangeSupport(this);

    /**
     * This is the constructor.
     * 
     * @param popTot
     *                        the total population
     * @param name
     *                        the name of the region
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
        this.climate = new ClimateImpl(humid, hot);
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
    public void incDeathPeople(final int death) {

        if (this.numDeath < popTot) {
            if (this.numDeath + death >= popTot) {
                if (this.numDeath + death > popTot) {
                    logger.warn("Too many death but I add those possible");
                }
                this.numDeath += this.popTot - this.numDeath;
                this.status = RegionCureStatus.FINISHED;
                this.getTrasmissionMeans().stream().forEach(k -> {
                    k.setState(MeansState.CLOSE);
                });
            } else {
                this.numDeath += death;
            }
            this.incOrDecInfectedPeople(- death);
        } else {
            logger.warn("The state is Finished");
        }
    }

    @Override
    public void incOrDecInfectedPeople(final int infected) {
        if (this.status != RegionCureStatus.FINISHED) {

            if(this.numInfected < popTot && (this.numInfected + infected) >= 0) {
                final int sum = this.numInfected + infected;
                if (sum >= this.popTot) {
                    if (sum > this.popTot) {
                        logger.warn("Too many infected but I add those possible");
                    }
                    infodataSupport.firePropertyChange("infectedRegion", this.numInfected, sum);
                    this.numInfected += popTot - this.numInfected;
                    System.out.println(this.numInfected);
                } else {
                    infodataSupport.firePropertyChange("infectedRegion", this.numInfected, sum);
                    this.numInfected += infected; 
                }
            } else if((this.numInfected + infected) < 0) {
                logger.warn("I can't remove this infect");
            }
        } else {
            logger.warn("State is already infected or RegionState is Finished");
        }
    }

    @Override
    public float calcPercInfected() {
        final float infect = this.numInfected;
        final float pop = this.popTot;
        return infect / pop;
    }

    @Override
    public int getNumInfected() {
        return numInfected;
    }

    @Override
    public int getNumDeath() {
        return numDeath;
    }

    /*
     * @Override
     * public int getNumCared() {
     * return numCared;
     * }
     */

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
    public Climate getClimate() {
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

    @Override
    public void initializeObserver(final PropertyChangeListener listener){
        this.infodataSupport.addPropertyChangeListener(listener);
    }

}
