package globaloutbreak.model.events;

/**
 * The interface of Event.
 */
public interface Event {

    /**
     * 
     * @return
     *         prob. of happening
     */
    float getProbOfHapp();

    /**
     * 
     * @return
     *         the event's name
     */
    String getName();

    /**
     * 
     * @return
     *         percentage of death
     */
    float getPercOfDeath();
}
