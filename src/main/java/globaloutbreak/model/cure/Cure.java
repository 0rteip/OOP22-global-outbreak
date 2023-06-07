package globaloutbreak.model.cure;

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
     * How daily research funds are allocated.
     */
    void research();

    /**
     * Returns if the cure is completed or not.
     * 
     * @return
     *         {@code True} if completed
     */
    boolean isCompleted();

    /**
     * Affects the research for a cure by a {@code changeFactor}.
     * 
     * @param changeFactor
     *                     influence factor
     */
    void increaseResearchDifficulty(float changeFactor);

    /**
     * Affects the progress done by a {@code changeFactor}.
     * 
     * @param changeFactor
     *                     influence factor
     */
    void reduceResearchProgress(float changeFactor);

    /**
     * @return true if the Cure is consistent
     */
    boolean isConsistent();

}
