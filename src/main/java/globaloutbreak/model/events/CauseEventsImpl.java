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
            final float prob = event.getProbOfHapp();
            final float num = RANDOM.nextFloat(0, 1);
            if (num <= prob) {
                final Region r = regions.get(RANDOM.nextInt(0, regions.size()));
                return Optional.of(
                        new ExtractedEventImpl(r.getColor(), event.getName(), calcDeath(r, event.getPercOfDeath())));
            }
        }
        return Optional.empty();
    }

    private long calcDeath(final Region region, final float percOfDeath) {
        long death = (long) Math.floor(region.getPopTot() * percOfDeath);
        if ((region.getNumDeath() + death) > region.getPopTot()) {
            death = region.getPopTot() - region.getNumDeath();
        }
        return death;
    }

}
