package globaloutbreak.model.region;

import java.util.List;

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
    void incDeathPeople(Integer dead);

    /**
     * This method increase(or decrease) the number of infected people.
     * 
     * @param infected 
     *                  the number of new infected people.
     * 
     */
    void incOrDecInfectedPeople(Integer infected);

    /**
     * This method calculates the percentage of infected.
     * 
     * @return
     *          the percentage of infected
     */
    Integer calcPercInfected();

    /**
     * 
     * @return
     *          the num of infected
     */
    Integer getNumInfected();

    /**
     * 
     * @return
     *          the num of death
     */
    Integer getNumDead();

    /**
     * 
     * @return
     *          the num of cared people
     */
    Integer getNumCared();

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
    Integer getPopTot();

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
    Integer getFacilities();

    /**
     * 
     * @return
     *          the region color
     */
    Integer getColor();

    /**
     * 
     * @return
     *          climate class
     */
    ClimateInt getClimate();

    /**
     * 
     * @return
     *          all means
     */
    List<TransmissionMeansImpl> getTrasmissionMeans();

}
