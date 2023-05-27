package globaloutbreak.model.impl;

import globaloutbreak.model.api.Priority;

/**
 * Possible priority status of the cure.
 */
public final class CurePriority implements Priority {

    private final int priority;
    private final String description;
    private final float resourcesPercentage;
    private final float detectionRate;

    private CurePriority(final int priority, final String description, final float resourcesPercentage,
            final float detectionRate) {
        this.priority = priority;
        this.description = description;
        this.resourcesPercentage = resourcesPercentage;
        this.detectionRate = detectionRate;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public float getResourcesPercentage() {
        return this.resourcesPercentage;
    }

    @Override
    public float getDetectionRate() {
        return this.detectionRate;
    }

    @Override
    public String toString() {
        return "CurePriority [priority=" + priority + ", description=" + description + ", resourcesPercentage="
                + resourcesPercentage + "]";
    }

    /**
     * Pattern builder: used here because:
     * 
     * - all the Priority instances should have a unique priority and to avoid
     * making the control logic public, everything is kept within the control of the
     * builder
     * 
     * - priority class has two parameters that can be easily confused, in a call to
     * its constructur, {@code int} abd {@code float} could be used both as
     * {@code int}.
     * 
     */
    @SuppressWarnings("PMD.LinguisticNaming")
    public static class Builder {

        private static final int PRIORITY = 0;
        private static final String DESCRIPTION = "None";
        private static final float RESOURCES_PERCENTAGE = 0;
        private static final float DETECTION_RATE = 0.2f;

        private int priority = PRIORITY;
        private String description = DESCRIPTION;
        private float resourcesPercentage = RESOURCES_PERCENTAGE;
        private int nextPriority = PRIORITY;
        private float detectionRate = DETECTION_RATE;

        /**
         * @param priority the priority
         * @return this builder, for method chaining
         */
        public Builder setPriority(final int priority) {
            this.priority = priority;
            return this;
        }

        /**
         * @param description the description
         * @return this builder, for method chaining
         */
        public Builder setDescription(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @param resourcesPercentage the resourcesPercentage
         * @return this builder, for method chaining
         */
        public Builder setResourcesPercentage(final float resourcesPercentage) {
            this.resourcesPercentage = resourcesPercentage;
            return this;
        }

        /**
         * @param detectionRate the detectionRate
         * @return this builder, for method chaining
         */
        public Builder setDetectionRate(final float detectionRate) {
            this.detectionRate = detectionRate;
            return this;
        }

        /**
         * @return a priority
         */
        public final Priority build() {
            if (this.priority != this.nextPriority) {
                throw new IllegalStateException("Incorrect priority");
            }
            this.nextPriority++;
            return new CurePriority(priority, description, resourcesPercentage, detectionRate);
        }
    }
}
