package globaloutbreak.model.cure.prioriry;

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
    int getPriority();

    /**
     * Returns the description of the priority.
     * 
     * @return
     *         description
     */
    String getDescription();

    /**
     * Returns the percentage of the used resources per Priority.
     * 
     * @return
     *         resources perventage
     */
    float getResourcesPercentage();

    /**
     * Returns the maximum detection rate value per priority.
     * 
     * @return
     *         detection rate
     */
    float getDetectionRate();

}
