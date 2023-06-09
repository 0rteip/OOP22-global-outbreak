package globaloutbreak.controller.voyage_cotroller;

import java.util.Map;

import globaloutbreak.model.pair.Pair;

/**
 * Interface of Voyage controller.
 */
public interface VoyageController {
    /**
     * 
     * @return
     *          extracted means
     */
    Map<String, Map<Integer, Pair<Integer, Integer>>> extractVoyages();
}
