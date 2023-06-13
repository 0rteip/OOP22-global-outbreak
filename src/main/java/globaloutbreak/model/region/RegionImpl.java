package globaloutbreak.model.region;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import globaloutbreak.model.cure.RegionCureStatus;
import globaloutbreak.model.pair.Pair;

/**
 * Implementation of Region.
 */
public final class RegionImpl implements Region {
    private long numInfected;
    private long numDeath;
    // private final int numCared;
    private long deathByEvents;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final long popTot;
    private final String name;
    private final float urban;
    private final float poor;
    private final int facilities;
    private final int color;
    private final Climate climate;
    private RegionCureStatus status = RegionCureStatus.NONE;
    // private State statusCure;
    private final List<TransmissionMean> trasmissionMeans = new LinkedList<>();
    private final PropertyChangeSupport infodataSupport = new PropertyChangeSupport(this);
    private final float closeMeans;

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
     * @param closeMeans
     *                       percentage of close borders
     * 
     */
    public RegionImpl(final long popTot, final String name,
            final Map<String, Pair<Integer, Optional<List<String>>>> reachableRegion, final float urban,
            final float poor, final int color, final int facilities, final float hot, final float humid,
            final float closeMeans) {
        this.popTot = popTot;
        this.name = name;
        this.urban = urban;
        this.poor = poor;
        this.color = color;
        this.facilities = facilities;
        this.climate = new ClimateImpl(humid, hot);
        this.deathByEvents = 0;
        this.closeMeans = closeMeans;
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
    public void incDeathPeople(final long death, final Boolean byEvent) {
        if ((this.numInfected - death) <= 0) {
            this.numInfected = 0;
            logger.warn("I can't remove this infect");
        } else {
            this.numInfected -= death;
        }
        if (this.numDeath < popTot) {
            if (this.numDeath + death >= popTot) {
                if (this.numDeath + death > popTot) {
                    logger.warn("Too many death but I add those possible");
                }
                if (byEvent) {
                    this.deathByEvents += this.popTot - this.numDeath;
                }
                this.numDeath = this.popTot;
                this.status = RegionCureStatus.FINISHED;

            } else {
                if (byEvent) {
                    this.deathByEvents += death;
                }
                this.numDeath += death;
            }
            checkAndCloseMeans();
        } else {
            logger.info("The state" + name + "is Finished");
        }
    }

    private void checkAndCloseMeans() {
        final float deathT = this.numDeath;
        final float popT = this.popTot;
        final float deathE = this.deathByEvents;
        if (((deathT - deathE) / popT) >= closeMeans && this.trasmissionMeans.get(0).getState() != MeansState.CLOSE) {
            this.getTrasmissionMeans().stream().forEach(k -> {
                k.setState(MeansState.CLOSE);
            });
            logger.info("Close " + this.name + " borders " + (deathT - deathE));
        }
    }

    @Override
    public void incOrDecInfectedPeople(final long infected) {
        if (infected > 0) {
            if (!this.status.equals(RegionCureStatus.FINISHED)) {
                if ((this.numInfected + this.numDeath) < popTot) {
                    final long sum = this.numInfected + infected + this.numDeath;
                    if (sum >= this.popTot) {
                        if (sum > this.popTot) {
                            logger.warn("Too many infected but I add those possible");
                        }
                        infodataSupport.firePropertyChange("infectedRegion", this.numInfected, sum);
                        this.numInfected += popTot - (this.numInfected + this.numDeath);
                    } else {
                        infodataSupport.firePropertyChange("infectedRegion", this.numInfected, sum);
                        this.numInfected += infected;
                    }
                }
            } else {
                logger.warn("State is already infected or RegionState is Finished");
            }
        }
    }

    @Override
    public float calcPercInfected() {
        final float infect = this.numInfected;
        final float pop = this.popTot;
        return infect / pop;
    }

    @Override
    public long getNumInfected() {
        return numInfected;
    }

    @Override
    public long getNumDeath() {
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
    public long getPopTot() {
        return popTot;
    }

    @Override
    public float getPoor() {
        return poor;
    }

    @Override
    public long getDeathByVirus() {
        return this.numDeath - this.deathByEvents;
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
    public void initializeObserver(final PropertyChangeListener listener) {
        this.infodataSupport.addPropertyChangeListener(listener);
    }

}
