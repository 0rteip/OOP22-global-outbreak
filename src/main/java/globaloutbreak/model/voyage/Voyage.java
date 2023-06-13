package globaloutbreak.model.voyage;

/**
 * Extracted Voyage.
 */
public interface Voyage {
    /**
     * 
     * @return
     *         type of means
     */
    String getType();

    /**
     * 
     * @return
     *         starting region(color)
     */
    int getPart();

    /**
     * 
     * @return
     *         region's color destination
     * 
     */
    int getDest();

    /**
     * 
     * @return
     *         new infected
     */
    long getInfected();
}
