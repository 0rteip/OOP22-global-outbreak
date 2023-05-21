package globaloutbreak.model.impl;

import globaloutbreak.model.api.Priority;

/**
 * Possible priority status of the cure.
 */
public class CurePriority implements Priority {

    private final int priority;
    private final String description;
    private final float resourcesPercentage;

    private CurePriority(final int priority, final String description, final float resourcesPercentage) {
        this.priority = priority;
        this.description = description;
        this.resourcesPercentage = resourcesPercentage;
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
    public static class Builder {

        private final static int PRIORITY = 0;

        private int priority = PRIORITY;
        private String description;
        private float resourcesPercentage;
        private int nextPriority = 0;

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
         * @return a priority
         */
        public final Priority build() {
            if (this.priority != this.nextPriority) {
                throw new IllegalStateException("Incorrect state");
            }
            this.nextPriority++;
            return new CurePriority(priority, description, resourcesPercentage);
        }
    }
}