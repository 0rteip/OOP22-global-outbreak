package globaloutbreak.model.events;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.Region;

public class CauseEvents implements CauseEventInt {
    private List<Event> events;
    /**
     * 
     * @param events
     */
    public CauseEvents(final List<Event> events) {
        this.events = events;
    }

    @Override
    public Optional<Pair<Region,Integer>> causeEvent(final List<Region> regions) {
        Random random = new Random();
        Event event = events.get(random.nextInt(0, events.size() - 1));
        if (random.nextInt(0, 100) <= event.getProbOfHapp()) {
            Region r = regions.get(random.nextInt(0, events.size() - 1));
            return Optional.of(new Pair<>(r, event.calcDeath(r.getPopTot())));
        } else {
            return Optional.empty();
        }
    }

}
