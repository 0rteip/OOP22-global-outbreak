package globaloutbreak.model.infodata;

import java.util.List;

import globaloutbreak.model.cure.CureData;
import globaloutbreak.model.region.Region;

/**
 * Info.
 */
public interface InfoData {

    /**
     * Increase Points.
     * 
     * @param points
     *               points
     */
    void increasePoints(int points);

    /**
     * Decrease Points.
     * 
     * @param points
     *               points
     */
    void decreasePoints(int points);

    /**
     * @return
     *         points
     */
    int getPoints();

    /**
     * @return
     *         total deaths
     */
    long getTotalDeaths();

    /**
     * @return
     *         total infected
     */
    long getTotalInfected();

    /**
     * 
     * @return
     *         total population
     */
    long getTotalPopulation();

    /**
     * @return
     *         cure data
     */
    CureData getCureData();

    /**
     * Update total deaths and infected.
     * 
     * @param regions
     */
    void updateTotalDeathsAndInfected(List<Region> regions);

    /**
     * update cure data.
     * 
     * @param cureData
     */
    void updateCureData(CureData cureData);

    /**
     * 
     * @param deaths
     */
    void updateDeaths(long deaths);
}
