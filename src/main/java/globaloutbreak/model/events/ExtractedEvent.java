package globaloutbreak.model.events;

/**
 * Interface of extrated event.
 */
public interface ExtractedEvent {
    /**
     * 
     * @return
     *         return region's color
     */
    int getRegion();

    /**
     * 
     * @return
     *         event's name
     */
    String getEvent();

    /**
     * 
     * @return
     *         new Death
     */
    long getDeath();
}
