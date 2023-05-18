package globaloutbreak.model.api;

/**
 * Possible priority status of the cure.
 */
public interface Priority {

    /**
     * Retrurs the priority of the cure. Higher is the priority higher is the
     * returned value.
     * 
     * @return
     *         priority
     */
    Integer getPriority();

    /**
     * Return the description of the priority.
     * 
     * @return
     *         description
     */
    String getDescription();

    /**
     * Return the percentage of the used resources per Priority.
     * 
     * @return
     *         resources perventage
     */
    float getResourcesPercentage();

}
