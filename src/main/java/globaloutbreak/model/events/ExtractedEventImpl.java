package globaloutbreak.model.events;

import globaloutbreak.model.region.Region;

/**
 * Implementation of ExtractedMeans.
 */
public final class ExtractedEventImpl implements ExtractedEvent {
    private Region region;
    private String event;
    private int death;
    
    public ExtractedEventImpl(final Region region, final String event, final int death ) {
        this.region = region;
        this.event = event;
        this.death = death;
    }
    @Override
    public Region getRegion() {
        return region;
    }
    @Override
    public String getEvent() {
        return event;
    }
    @Override
    public int getDeath() {
        return death;
    }

}
