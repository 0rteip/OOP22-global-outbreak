package globaloutbreak.model.impl;

/**
 * Possible status of the {@link globaloutbreak.model.api.Cure} for single
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
