package globaloutbreak.model.infodata;

import globaloutbreak.model.cure.CureData;

/**
 * Info.
 */
public interface InfoData {

    /**
     * @param 
     * points
     */
    void increasePoints(int points);

    /**
     * @param 
     * points
     */
    void decreasePoints(int points);

    /**
     * @return
     * points
     */
    int getPoints();

    /**
     * @return
     * total deaths
     */
    long getTotalDeaths();

    /**
     * @return
     * total infected
     */
    long getTotalInfected();

    /**
     * 
     * @return
     * total population
     */
    long getTotalPopulation();
    /**
     * @return
     * cure data
     */
    CureData getCureData();

    /**
     * update total deaths and infected.
     * @param numDeaths
     * @param numInfected
     */
    void updateTotalDeathsAndInfected(long numDeaths, long numInfected);

    /**
     * update cure data.
     * 
     * @param
     * cureData
     */
    void updateCureData(CureData cureData);

    /**
     * 
     * @param deaths
     */
    void updateDeaths(long deaths);
}

