package globaloutbreak.gamespeed;

/**
 * Speed of the game.
 */
public final class GameSpeed {

    private final float duration;
    private final String name;
    private final boolean isDefault;

    private GameSpeed(final float duration, final String name, final boolean isDefault) {
        this.duration = duration;
        this.name = name;
        this.isDefault = isDefault;
    }

    /**
     * @return
     *         the duration in seconds of the gameSpeed
     */
    public float getDuration() {
        return this.duration;
    }

    /**
     * Returns the default GameSpeed.
     * 
     * @return
     *         GameSPeed
     */
    public boolean isDefault() {
        return this.isDefault;
    }

    /**
     * @return
     *         the String rapresentation of the gameSpeed
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Builder for GameSpeed.
     */
    @SuppressWarnings("PMD.LinguisticNaming")
    public static class Builder {

        private static final float DURATION = 1;
        private static final String NAME = "DEFAULT";
        private static final boolean IS_DEFAULT = false;

        private float duration = DURATION;
        private String name = NAME;
        private boolean isDefault = IS_DEFAULT;
        private boolean consumed;

        /**
         * @param name the name
         * @return this builder, for method chaining
         */
        public Builder setName(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @param duration the duration
         * @return this builder, for method chaining
         */
        public Builder setDuration(final float duration) {
            this.duration = duration;
            return this;
        }

        /**
         * @param isDefault iv value is the default one
         * @return thvalueis builder, for method chaining
         */
        public Builder setDefault(final boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        /**
         * @return a gamespeed
         */
        public final GameSpeed build() {
            if (consumed) {
                throw new IllegalStateException("The builder can only be used once");
            }
            consumed = true;
            return new GameSpeed(duration, name, isDefault);
        }
    }

}
