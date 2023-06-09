package globaloutbreak.controller.event_controller;

import java.util.Optional;

import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.Region;

/**
 * Interface of Event controller.
 */
public interface EventController {
    /**
     * 
     * @return
     *          region and num of death
     */
    Optional<Pair<Region, Integer>> causeEvent();
}
