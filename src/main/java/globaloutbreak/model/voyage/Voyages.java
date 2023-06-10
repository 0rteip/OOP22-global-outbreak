package globaloutbreak.model.voyage;

import java.util.List;
import java.util.Map;
import globaloutbreak.model.region.Region;

/**
 * 
 */

public interface Voyages {

    /**
     * This method extrat voyages.
     * 
     * @param regions
     *                a list of regions
     * @param pot
     *                extra poten.
     * @return
     *         the string is the name of mean
     *         in the second map the integer is the number of new infected
     *         the pair is the departure and destination expressed with the color of
     *         the region
     */
    List<VoyageM> extractMeans(List<Region> regions, Map<String, Float> pot);
    /**
     * 
     * @return
     *         name of means
     */
    List<String> getMeans();
}
