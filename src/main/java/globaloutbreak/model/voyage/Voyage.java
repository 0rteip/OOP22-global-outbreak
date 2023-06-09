package globaloutbreak.model.voyage;

import java.util.List;
import java.util.Map;

import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.Region;

/**
 * 
 */

public interface Voyage {

    /**
     * This method extrat voyages.
     * 
     * @param regions
     *                a list of regions
     * @return
     *         the string is the name of mean
     *         in the second map the integer is the number of new infected
     *         the pair is the departure and destination expressed with the color of
     *         the region
     */
    Map<String, Map<Integer, Pair<Integer, Integer>>> extractMeans(List<Region> regions);
}
