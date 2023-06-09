package globaloutbreak.model.infodata;

import globaloutbreak.model.cure.CureData;

/**
 * Interface to manage DnaPoints.
 */
public interface InfoData {

    /**
     * Increase points.
     * 
     * @param points
     *               points to be added.
     */
    void increasePoints(int points);

    /**
     * Decrease points.
     * 
     * @param points
     *               points to be decreased.
     */
    void decreasePoints(int points);

    /**
     * 
     * @return
     *         the points owned.
     */
    int getPoints();

    /**
     * 
     * @return
     * total deaths
     */
    int getTotalDeaths();

    /**
     * 
     * @return
     * total infected
     */
    int getTotalInfected();

    /**
     * 
     * @return
     * cure data
     */
    CureData getCureData();

    /**
     * update total deaths.
     */
    void updateTotalDeathsAndInfected(int totalDeaths, int totalInfected);

    /**
     * update Cure Status.
     */
    void updateCureStatus(CureData cureData);
}
