package globaloutbreak.model.cure;

/**
 * Possible status of the {@link globaloutbreak.model.cure.Cure} for single
 * {@link globaloutbreak.model.api.Region}.
 */
public enum RegionCureStatus {
    /**
     * Disease not discovered.
     */
    NONE,
    /**
     * Disease discovered, but cured not started.
     */
    DISCOVERED,
    /**
     * Cure started.
     */
    STARTED,
    /**
     * Cure finished.
     */
    FINISHED;
}
