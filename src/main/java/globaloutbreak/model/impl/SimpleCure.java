package globaloutbreak.model.impl;

import java.util.List;

import globaloutbreak.model.api.Cure;
import globaloutbreak.model.api.CureData;
import globaloutbreak.model.api.Priority;
import javafx.scene.layout.Region;

/**
 * SimpleCure is a basic implementation of {@link Cure}.
 */
public class SimpleCure implements Cure {

    private final List<Region> regions;
    private final List<Priority> priorities;
    private boolean isComplete;

    /**
     * Creates a {@link Cure} which is managed in the given {@link Region}
     * and which has the given {@link Priority} levels.
     * 
     * @param regions
     *                   Regions that research for the cure
     * @param priorities
     *                   Priority types
     */
    public SimpleCure(final List<Region> regions, final List<Priority> priorities) {
        this.regions = regions;
        this.priorities = priorities;
    }

    @Override
    public CureData getGlobalStatus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGlobalStatus'");
    }

    @Override
    public void research() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'research'");
    }

    @Override
    public boolean isCompleted() {
        return this.isComplete;
    }

}
