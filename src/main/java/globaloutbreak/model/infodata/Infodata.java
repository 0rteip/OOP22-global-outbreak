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
    int getTotalDeaths();

    /**
     * @return
     * total infected
     */
    int getTotalInfected();

    /**
     * 
     * @return
     * total population
     */
    int getTotalPopulation();
    /**
     * @return
     * cure data
     */
    CureData getCureData();

    /**
     * update total deaths and infected.
     */
    void updateTotalDeathsAndInfected(int numDeaths, int numInfected);

    /**
     * update cure data.
     */
    void updateCureData(CureData cureData);
}
