package globaloutbreak.model.events;

/**
 * Implementation of ExtractedMeans.
 */
public final class ExtractedEventImpl implements ExtractedEvent {
    private final int region;
    private final String event;
    private final long death;

    /**
     * 
     * @param region
     *               region
     * @param event
     *               event's name
     * @param death
     *               new death
     */
    public ExtractedEventImpl(final int region, final String event, final long death) {
        this.region = region;
        this.event = event;
        this.death = death;
    }

    @Override
    public int getRegion() {
        return this.region;
    }

    @Override
    public String getEvent() {
        return this.event;
    }

    @Override
    public long getDeath() {
        return this.death;
    }

}
