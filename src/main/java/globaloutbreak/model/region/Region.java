package globaloutbreak.model.region;

import globaloutbreak.model.cure.RegionCureStatus;

/**
 * 
 */
public interface Region {

    /**
     * Number of facilities.
     * 
     * @return
     *         facilities
     */
    int getFacilities();

    /**
     * Returns total poulation.
     * 
     * @return
     *         population
     */
    int getTotalPopulation();

    /**
     * Returns number of deaths.
     * 
     * @return
     *         deaths
     */
    int getDeath();

    /**
     * QUalcoa.
     * 
     * @param started
     *                altr
     */
    void setCureStatus(RegionCureStatus started);

    /**
     * Wuadas.
     * 
     * @return
     *         asdsada
     */
    RegionCureStatus getCureStatus();

    /**
     * 
     * @return
     *         num
     */
    int getNumInfected();

    /**
     * 
     * @param calculateNewDeaths
     *                          death
     */
    void incDeathPeople(int calculateNewDeaths);

    /**
     * 
     * @param i
     *          num
     */
    void incOrDecNuminfected(int i);

    /**
     * 
     * @return
     *         urban
     */
    float getUrban();

    /**
     * 
     * @return
     *         climate
     */
    ClimateImpl getClimateImpl();

    /**
     * 
     * @return
     *         poor
     */
    float getPoor();

    /**
     * 
     * @return
     *         pop
     */
    int getPopTot();

}
