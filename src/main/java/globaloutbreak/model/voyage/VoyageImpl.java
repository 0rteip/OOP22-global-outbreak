package globaloutbreak.model.voyage;

import globaloutbreak.model.region.Region;

/**
 * Implement. of Voyage.
 */
public final class VoyageImpl implements Voyage {
    private final String type;
    private final Region part;
    private final Region dest;
    private final long infected;

    /**
     * 
     * @param type
     *                 type of means
     * @param part
     *                 starting region's color
     * @param dest
     *                 destination region's color
     * @param infected
     *                 new infect
     */
    public VoyageImpl(final String type, final Region part,
            final Region dest, final long infected) {
        this.type = type;
        this.part = part;
        this.dest = dest;
        this.infected = infected;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public Region getPart() {
        return this.part;
    }

    @Override
    public Region getDest() {
        return this.dest;
    }

    @Override
    public long getInfected() {
        return this.infected;
    }

}
