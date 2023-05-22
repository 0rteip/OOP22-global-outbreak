package globaloutbreak.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.model.api.Cure;
import globaloutbreak.model.api.CureData;
import globaloutbreak.model.api.Priority;
import globaloutbreak.model.api.Region;

/**
 * SimpleCure is a basic implementation of {@link Cure}.
 */
public class SimpleCure implements Cure {

    private final static Logger logger = LoggerFactory.getLogger(SimpleCure.class);

    private final float dailyBudget;
    private final int numberOfMajorContributors;
    private final Map<Region, Float> contributions;
    private final float researchersEfficiency;
    private int daysBeforeStartResearch;
    private final float detectionRate;
    private final List<Priority> priorities;
    private float necessaryBudget;
    private float researchBudget;
    private int currentPriority;
    private boolean isStarted;
    private boolean isComplete;

    private SimpleCure(final float dailyBudget, final int numberOfMajorContributors,
            final Map<Region, Float> contributions,
            final float researchersEfficiency, final List<Priority> priorities, final float necessaryBudget,
            final float researchBudget,
            final int currentPriority, final int daysBeforeStartResearch, final float detectionRate) {
        this.dailyBudget = dailyBudget;
        this.numberOfMajorContributors = numberOfMajorContributors;
        this.contributions = contributions;
        this.researchersEfficiency = researchersEfficiency;
        this.priorities = priorities;
        this.necessaryBudget = necessaryBudget;
        this.researchBudget = researchBudget;
        this.currentPriority = currentPriority;
        this.daysBeforeStartResearch = daysBeforeStartResearch;
        this.detectionRate = detectionRate;
        this.isStarted = false;
        this.isComplete = false;
    }

    @Override
    public CureData getGlobalStatus() {
        return new CureData() {

            @Override
            public int getProgress() {
                return cureProgress();
            }

            @Override
            public int getRemainingDays() {
                return 0;
            }

            @Override
            public List<Region> getMajorContributors() {
                return isStarted ? contributions.entrySet().stream()
                        .sorted((e0, e1) -> Float.compare(e1.getValue(), e0.getValue()))
                        .map(el -> el.getKey())
                        .limit(numberOfMajorContributors)
                        .toList() : new ArrayList<>();
            }

            @Override
            public Priority gePriority() {
                return priorities.get(currentPriority);
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
        if (this.isStarted) {
            this.contributions.entrySet().stream()
                    .filter(el -> el.getKey().hasStartedResearch())
                    .forEach(el -> this.contributions.compute(el.getKey(),
                            (key, val) -> val + this.dailyRegionContribution(key)));
            this.researchBudget = this.contributions.entrySet().stream()
                    .map(el -> el.getValue())
                    .reduce(Float.valueOf(0), (e0, e1) -> e0 + e1);
        } else {
            // se uno stato ha individuato la malattia e sono passati
            // 'daysBeforeStartResearch' giorni comincia a sviluppare la cura
            if (numberOfRegionsTahtDiscoveredDisease() > 0 && this.daysBeforeStartResearch-- == 0) {
                System.out.println("Starting cure");
                increasePriority();
                this.isStarted = true;
                this.contributions.entrySet().stream()
                        .filter(el -> el.getKey().getStatus() == "Discovered")
                        .forEach(el -> el.getKey().setStatus("Started"));
            } else {
                this.contributions.entrySet().stream()
                        .filter(el -> Float.valueOf(el.getKey().getDeath())
                                / el.getKey().getTotalPopulation() > this.detectionRate)
                        .forEach(el -> el.getKey().setStatus("Discovered"));
            }
        }

        if (cureProgress() >= 100) {
            this.isComplete = true;
        }
    }

    @Override
    public boolean isCompleted() {
        return this.isComplete;
    }

    @Override
    public void increaseResearchDifficulty(final float changeFactor) {
        this.necessaryBudget *= changeFactor;
    }

    @Override
    public void reduceResearchProgress(final float changeFactor) {
        this.researchBudget *= changeFactor;
    }

    @Override
    public boolean isConsistent() {
        if (this.priorities.isEmpty()) {
            logger.warn("Priority list can't be empty");
            return false;
        }
        if (this.contributions.isEmpty()) {
            logger.warn("Regions list can't be empty");
            return false;
        }
        if (this.priorities.stream()
                .filter(el -> el.getPriority() == this.currentPriority)
                .toList().size() != 1) {
            logger.warn("Invalid current prioriry: current priority '{}' is not found in the priorities '{}'",
                    this.currentPriority, this.priorities);
            return false;
        }
        if (this.necessaryBudget < this.researchBudget) {
            logger.warn("Research budget '{}' must be lower than necessary budget '{}'",
                    this.researchBudget, this.necessaryBudget);
            return false;
        }

        return checkIfPositive(this.dailyBudget, "dailyBudget") &&
                checkIfPositive(this.numberOfMajorContributors, "numberOfMajorContributors") &&
                checkIfPositive(this.researchersEfficiency, "researchersEfficiency") &&
                checkIfPositive(this.necessaryBudget, "necessaryBudget") &&
                checkIfPositive(this.researchBudget, "researchBudget") &&
                checkIfPositive(this.currentPriority, "currentPriority");
    }

    private float dailyRegionContribution(final Region region) {
        return (1 - Float.valueOf(region.getDeath()) / region.getTotalPopulation()) *
                region.getFacilities() *
                this.researchersEfficiency *
                this.priorities.get(this.currentPriority).getResourcesPercentage() *
                this.dailyBudget;
    }

    private int cureProgress() {
        final int progress = Math.round(this.researchBudget / this.necessaryBudget * 100);
        if (progress >= 100) {
            this.researchBudget = this.necessaryBudget;
            return 100;
        }
        return progress;
    }

    private boolean checkIfPositive(final float number, final String name) {
        if (number < 0) {
            logger.warn("Value {} can't be negative", name);
            return false;
        }
        return true;
    }

    private int numberOfRegionsTahtDiscoveredDisease() {
        return this.contributions.entrySet().stream()
                .filter(el -> el.getKey().getStatus() == "Discovered")
                .toList().size();
    }

    private void increasePriority() {
        this.priorities.stream()
                .filter(el -> el.getPriority() == this.currentPriority + 1)
                .findAny().ifPresent(el -> this.currentPriority = el.getPriority());
    }

    /**
     * Pattern builder: used here because:
     * 
     * - all the parameters of the SimpleCure class have a default value, execpt for
     * priorities and contributions, and we would like to have all the possible
     * contructors for the cure, to specialize it, too many to separate them all.
     * 
     * - multiple parameters are of type float and it means that not all the
     * costructor
     * could exist.
     * 
     * - some value could be either be confused when inizialized.
     * 
     */
    @SuppressWarnings("PMD.LinguisticNaming")
    public static class Builder {

        private static final float DAILY_BUDGET = 1_146.56f;
        private static final int NUMBER_OF_MAJOR_CONTRIBUTORS = 3;
        private static final float RESEARCHERS_EFFICIENCY = 1;
        private static final float NECESSARY_BUDGET = 25_000_000;
        private static final float RESEARCH_BUDGET = 0;
        private static final int CURRENT_PRIORITY = 0;
        private static final int DAYS_BEFORE_START_RESEARCH = 10;
        private static final float DETECTION_RATE = 0.2f;

        private float dailyBudget = DAILY_BUDGET;
        private int numberOfMajorContributors = NUMBER_OF_MAJOR_CONTRIBUTORS;
        private float researchersEfficiency = RESEARCHERS_EFFICIENCY;
        private float necessaryBudget = NECESSARY_BUDGET;
        private float researchBudget = RESEARCH_BUDGET;
        private int currentPriority = CURRENT_PRIORITY;
        private int daysBeforeStartResearch = DAYS_BEFORE_START_RESEARCH;
        private float detectionRate = DETECTION_RATE;
        private final List<Priority> priorities;
        private final Map<Region, Float> contributions = new HashMap<>();
        private boolean consumed;

        /**
         * The Builder for a {@link SimpleCure} which is managed in the given
         * {@link Region}
         * and which has the given {@link Priority} levels.
         * 
         * @param regions
         *                   Regions that research for the cure
         * @param priorities
         *                   Priority types
         */
        public Builder(final List<Region> regions, final List<Priority> priorities) {
            if (regions.size() != 0) {
                regions.forEach(el -> this.contributions.put(el, 0f));
            }
            this.priorities = priorities;
        }

        /**
         * @param dailyBudget the daily budget that every facility can use at max
         * @return this builder, for method chaining
         */
        public Builder setDailyBudget(final float dailyBudget) {
            this.dailyBudget = dailyBudget;
            return this;
        }

        /**
         * @param numberOfMajorContributors the number of the states with the major
         *                                  contribution to the cure
         * @return this builder, for method chaining
         */
        public Builder setNumberOfMajorContributors(final int numberOfMajorContributors) {
            this.numberOfMajorContributors = numberOfMajorContributors;
            return this;
        }

        /**
         * @param researchersEfficiency the efficiency of the researchers
         * @return this builder, for method chaining
         */
        public Builder setResearchersEfficiency(final float researchersEfficiency) {
            this.researchersEfficiency = researchersEfficiency;
            return this;
        }

        /**
         * @param necessaryBudget the necessary budget to complete the cure
         * @return this builder, for method chaining
         */
        public Builder setNecessaryBudget(final float necessaryBudget) {
            this.necessaryBudget = necessaryBudget;
            return this;
        }

        /**
         * @param researchBudget the research budget
         * @return this builder, for method chaining
         */
        public Builder setResearchBudget(final float researchBudget) {
            this.researchBudget = researchBudget;
            return this;
        }

        /**
         * @param currentPriority the current priority
         * @return this builder, for method chaining
         */
        public Builder setCurrentPriority(final int currentPriority) {
            this.currentPriority = currentPriority;
            return this;
        }

        /**
         * @param daysBeforeStartResearch days between discovery and research
         * @return this builder, for method chaining
         */
        public Builder setDaysBeforeStartResearch(final int daysBeforeStartResearch) {
            this.daysBeforeStartResearch = daysBeforeStartResearch;
            return this;
        }

        /**
         * @param detectionRate with what percentage of deaths are diseases detected
         * @return this builder, for method chaining
         */
        public Builder setDetectionRate(final float detectionRate) {
            this.detectionRate = detectionRate;
            return this;
        }

        /**
         * @return a SimpleCure
         */
        public final SimpleCure build() {
            if (consumed) {
                throw new IllegalStateException("The builder can only be used once");
            }
            consumed = true;

            return new SimpleCure(dailyBudget, numberOfMajorContributors, contributions,
                    researchersEfficiency,
                    priorities, necessaryBudget, researchBudget, currentPriority, daysBeforeStartResearch,
                    detectionRate);
        }
    }

    @Override
    public String toString() {
        return "SimpleCure [dailyBudget=" + dailyBudget + ", numberOfMajorContributors=" + numberOfMajorContributors
                + ", contributions=" + contributions + ", researchersEfficiency=" + researchersEfficiency
                + ", priorities=" + priorities + ", necessaryBudget=" + necessaryBudget + ", researchBudget="
                + researchBudget + ", currentPriority=" + currentPriority + ", isStarted=" + isStarted + ", isComplete="
                + isComplete + "]";
    }
}
