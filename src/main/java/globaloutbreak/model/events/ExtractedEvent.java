package globaloutbreak.model.events;

import globaloutbreak.model.region.Region;
/**
 * Interface of extrated event.
 */
public interface ExtractedEvent {
    /**
     * 
     * @return
     *          return region
     */
    Region getRegion();
    /**
     * 
     * @return
     *          event's name
     */
    String getEvent();
    /**
     * 
     * @return
     *          new Death
     */
    int getDeath();
}
