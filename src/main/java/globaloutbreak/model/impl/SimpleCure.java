package globaloutbreak.model.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import globaloutbreak.model.api.Cure;
import globaloutbreak.model.api.CureData;
import globaloutbreak.model.api.Priority;
import globaloutbreak.model.api.Region;

/**
 * SimpleCure is a basic implementation of {@link Cure}.
 */
public class SimpleCure implements Cure {

    private final float dailyBudget = 1_146.56f;
    private final Map<Region, Float> contributions = new HashMap<>();
    private final Float researchersEfficiency = 0.7f;
    private final List<Priority> priorities;
    private float necessaryBudget = 25_000_000;
    private float researchBudget = 0;
    private int currentPriority = 0;
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
        regions.forEach(el -> contributions.put(el, 0f));
        this.priorities = priorities;
    }

    @Override
    public CureData getGlobalStatus() {
        return new CureData() {

            @Override
            public Integer getProgress() {
                return Math.round(researchBudget / necessaryBudget * 100);
            }

            @Override
            public Integer getRemainingDays() {
                return null;
            }

            @Override
            public List<Region> getMajorContributors() {
                return contributions.entrySet().stream()
                        .sorted((e0, e1) -> Float.compare(e1.getValue(), e0.getValue()))
                        .map(el -> el.getKey())
                        .limit(3)
                        .toList();
            }

            @Override
            public Priority gePriority() {
                return priorities.stream()
                        .filter(el -> el.getPriority() == currentPriority)
                        .findFirst().orElseThrow(() -> new IllegalStateException("Invalid priority"));
            }

            @Override
            public String toString() {
                return "CureData [" + gePriority() + ", progress=" + getProgress() + ", contrib="
                        + getMajorContributors() + ", days=" + getRemainingDays() + "]";
            }
        };
    }

    @Override
    public void research() {
        this.contributions.entrySet().stream()
                .filter(el -> el.getKey().hasStartedResearch())
                .forEach(el -> this.contributions.compute(el.getKey(),
                        (key, val) -> val + this.dailyRegionContribution(key)));
    }

    @Override
    public boolean isCompleted() {
        return this.isComplete;
    }

    private Float dailyRegionContribution(Region region) {
        return region.getTotalPopulation() / region.getDeath() *
                region.getFacilities() *
                this.researchersEfficiency *
                this.priorities.get(this.currentPriority).getResourcesPercentage() *
                this.dailyBudget;
    }

}
