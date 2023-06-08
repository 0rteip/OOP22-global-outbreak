package globaloutbreak.controller.region_controller;

import globaloutbreak.model.region.Region;

/**
 * Controller for Region
 */
public interface RegionControllerInt {

    /**
     * This method increase the deaths of a region.
     * 
     * @param newdeath 
     *                  dead to add
     * @param region
     *                  the region
     */
    void incDeathPeople(Integer newdeath, Region region);

    /**
     * This method increase the infected of a region.
     * 
     * @param newInfect
     *                  infected to add
     * @param region
     *                  the region
     */
    void incOrDecInfectedPeople(Integer newInfect, Region region);

    /**
     * This method find the death count by the region color.
     * 
     * @param color 
     *              the region color
     * @return
     *          return the count of death
     */
    Integer getDeath(Integer color);

    /**
     * This method find the infecded count by the region color.
     * 
     * @param color
     *              the region color
     * @return
     *          return the count of infected people
     */
    Integer getInfect(Integer color);

    /**
     * This method find the name of region by the region color.
     * @param color
     *              the region color
     * @return
     *          return the name of region
     */
    String getName(Integer color);
}
