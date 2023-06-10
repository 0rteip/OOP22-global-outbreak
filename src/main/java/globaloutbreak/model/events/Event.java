package globaloutbreak.model.events;

import java.beans.PropertyChangeListener;

/**
 * The interface of Event.
 */
public interface Event {

    /**
     * 
     * @param popTot
     *               total population
     * @return
     *         the number of death
     */
    long calcDeath(long popTot);

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
     * Add the property change listener for infoData.
     */
    void initializeObserver(PropertyChangeListener listener);
}
