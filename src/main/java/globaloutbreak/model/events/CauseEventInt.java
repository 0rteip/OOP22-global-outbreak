package globaloutbreak.model.events;

import java.util.List;
import java.util.Optional;

import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.Region;
/**
 * Interface of Casuse Event.
 */
public interface CauseEventInt {
  
    /**
     * This method cause a event.
     * 
     * @param regions
     *                  list of region
     * @return
*              the number of deaths it caused ande the Region 
     */
    public Optional<Pair<Region, Integer>> causeEvent( List<Region> regions);
}
