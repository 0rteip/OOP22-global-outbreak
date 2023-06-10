package globaloutbreak.model.voyage;

/**
 * Extracted Voyage.
 */
public interface Voyage {
    /**
     * 
     * @return
     *          type of means
     */
    String getType();

    /**
     * 
     * @return
     *          starting region.
     */
    int getPart();
    /**
     * 
     * @return
     *          destination region
     */
    int getDest();
    /**
     * 
     * @return
     *          new infected
     */
    int getInfected();
}
