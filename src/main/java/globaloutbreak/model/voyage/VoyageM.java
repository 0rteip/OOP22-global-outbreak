package globaloutbreak.model.voyage;

import globaloutbreak.model.region.Region;

/**
 * Extracted Voyage.
 */
public interface VoyageM {
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
    Region getPart();
    /**
     * 
     * @return
     *          destination region
     */
    Region getDest();
    /**
     * 
     * @return
     *          new infected
     */
    int getInfected();
}
