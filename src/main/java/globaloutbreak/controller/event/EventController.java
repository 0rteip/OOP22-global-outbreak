package globaloutbreak.controller.event;
import java.util.List;
import globaloutbreak.model.events.Event;

/**
 * Interface of Event controller.
 */
public interface EventController {
    /**
     * 
     * @return
     *         event's list
     */
    List<Event> createEvents();
}
