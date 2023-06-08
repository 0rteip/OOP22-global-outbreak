package globaloutbreak.model.cure;

import java.util.List;
import globaloutbreak.model.region.Region;

/**
 * An interface for a reader of SimpleCure.
 */
public interface SimpleCureReader {

    /**
     * Returns a {@link SimpleCure}
     * 
     * @param regions
     *                list of region that contribute the cure
     * 
     * @return
     *         SimpleCure
     */
    SimpleCure getSimpleCure(List<Region> regions);
}
