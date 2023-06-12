package globaloutbreak.model.voyage;

/**
 * Implement. of Voyage.
 */
public final class VoyageImpl implements Voyage {
    private final String type;
    private final int part;
    private final int dest;
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
    public VoyageImpl(final String type, final int part,
            final int dest, final long infected) {
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
    public int getPart() {
        return this.part;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public long getInfected() {
        return this.infected;
    }

}
