package globaloutbreak.model.region;

import java.beans.PropertyChangeListener;
import java.util.List;

import globaloutbreak.model.cure.RegionCureStatus;

/**
 * Interface of RegionImpl.
 */
public interface Region {

    /**
     * This method increase the number of death people.
     * 
     * @param dead the number of new death people.
     * 
     */
    void incDeathPeople(long dead);

    /**
     * This method increase(or decrease) the number of infected people.
     * 
     * @param infected 
     *                  the number of new infected people.
     * 
     */
    void incOrDecInfectedPeople(long infected);

    /**
     * This method calculates the percentage of infected.
     * 
     * @return
     *          the percentage of infected
     */
    float calcPercInfected();

    /**
     * 
     * @return
     *          the num of infected
     */
    long getNumInfected();

    /**
     * 
     * @return
     *          the num of death
     */
    long getNumDeath();
/* 
    /**
     * 
     * @return
     *          the num of cared people
     *//* 
    int getNumCared();
    */
    /**
     * 
     * @return
     *          region Cure status
     */
    RegionCureStatus getCureStatus();

    /**
     * 
     * @return
     *          the region name
     */
    String getName();

    /**
     * 
     * @return
     *          urban percentage
     */
    float getUrban();

    /**
     * 
     * @return
     *          total population
     */
    long getPopTot();

    /**
     * 
     * @return
     *          poor perc
     */
    float getPoor();

    /**
     * 
     * @return
     *          the cure facilities
     */
    int getFacilities();

    /**
     * 
     * @return
     *          the region color
     */
    int getColor();

    /**
     * 
     * @return
     *          climate class
     */
    Climate getClimate();

    /**
     * 
     * @return
     *          all means
     */
    List<TransmissionMean> getTrasmissionMeans();

    /**
     * This method change the status.
     * 
     * @param started
     *                  new status
     */
    void setCureStatus(RegionCureStatus started);

    /**
     * Add the property change listener for infoData.
     */
    void initializeObserver(PropertyChangeListener listener);
}
