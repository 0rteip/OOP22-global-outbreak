package globaloutbreak.model.infodata;

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
    Integer getPoints();
}
