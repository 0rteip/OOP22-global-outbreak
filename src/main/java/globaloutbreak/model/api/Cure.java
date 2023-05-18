package globaloutbreak.model.api;

/**
 * Interface for a Cure.
 */
public interface Cure {

    /**
     * Returns a float representing the progress of the cure.
     * 
     * @return
     *         cure status
     */
    CureData getGlobalStatus();

    /**
     * How research founds are allocated.
     */
    void research();

    /**
     * Returns if the cure is completed or not.
     * 
     * @return
     *         {@code True} if completed
     */
    boolean isCompleted();

}
