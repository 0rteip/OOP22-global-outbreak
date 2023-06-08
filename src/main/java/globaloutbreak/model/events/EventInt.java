package globaloutbreak.model.events;

/**
 * The interface of Event.
 */
public interface EventInt {

    /**
     * 
     * @param popTot
     *              total population
     * @return
     *          the number of death
     */
    Integer calcDeath(Integer popTot);

    /**
     * 
     * @return
     *          prob. of happening
     */
    float getProbOfHapp();

    /**
     * 
     * @return
     *          the event's name
     */
    String getName();

}
