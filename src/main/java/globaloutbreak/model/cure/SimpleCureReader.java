package globaloutbreak.model.cure;

import java.util.List;

import globaloutbreak.model.region.Region;

/**
 * An interface for a reader of simplecure.
 */
public interface SimpleCureReader {

    /**
     * Returns a SimpleCure.
     * 
     * @param regions
     *                list of regions that contribute
     * @return
     *         simplecure
     */
    SimpleCure getSimpleCure(List<Region> regions);

}
