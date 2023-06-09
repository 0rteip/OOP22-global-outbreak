package globaloutbreak.controller.region;

import java.util.List;

import globaloutbreak.model.region.Region;

/**
 * Controller for Region.
 */
public interface RegionController {
    /**
     * 
     * @return
     *          list of Region
     */
    List<Region> getRegions();
    /**
     * 
     * @param color
     *              region's color
     * @return
     *          region
     */
    Region findRegionByColor(int color);
}
