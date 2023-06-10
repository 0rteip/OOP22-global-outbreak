package globaloutbreak.model.voyage;

import globaloutbreak.model.region.Region;

/**
 * Implement. of Voyage.
 */
public final class VoyageMImpl implements VoyageM {
    private final String type;
    private final Region part;
    private final Region dest;
    private final int infected;
    /**
     * 
     * @param type
     *              type of means
     * @param region
     *              starting region
     * @param region2
     *                 destination region
     * @param infected
     *                  new infect
     */
    public VoyageMImpl(final String type, final Region region,
            final Region region2, final int infected) {
        this.type = type;
        this.part = region;
        this.dest = region2;
        this.infected = infected;
    }

    @Override
    public String getType() {
        return type;
    }
    @Override
    public Region getPart() {
        return part;
    }
    @Override
    public Region getDest() {
        return dest;
    }
    @Override
    public int getInfected() {
        return infected;
    }

}
