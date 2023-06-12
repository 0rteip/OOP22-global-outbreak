package globaloutbreak.model.events;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import globaloutbreak.model.region.Region;

/**
 * Implement. of CauseEventInt.
 */
public final class CauseEventsImpl implements CauseEvent {
    private final List<Event> events;
    private static final Random RANDOM = new Random();

    /**
     * 
     * @param events
     */
    public CauseEventsImpl(final List<Event> events) {
        this.events = new LinkedList<>(events);
    }

    @Override
    public Optional<ExtractedEvent> causeEvent(final List<Region> regions) {
        if (!regions.isEmpty()) {
            
            final Event event = events.get(RANDOM.nextInt(0, events.size()));
            float prob = event.getProbOfHapp();
            float num = RANDOM.nextFloat(0, 1);
            if (num <= prob) {
                final Region r = regions.get(RANDOM.nextInt(0, regions.size() - 1));
                return Optional.of(new ExtractedEventImpl(r.getColor(), event.getName(), event.calcDeath(r.getPopTot())));
            }
        }
        return Optional.empty();
    }

}
