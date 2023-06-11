package globaloutbreak.model.endcauses;

/**
 * Cases for ending the game.
 */
public enum EndCauses {
    /**
     * Cure is developed.
     */
    CURE_DEVELOPED("Cure was developed and word is safe, you lose.."),
    /**
     * Population is totally annihilared.
     */
    POPULATION_ANNIHILATED("Word is totally dead, you win.."),
    /**
     * There are no more infected people.
     */
    NO_INFECTED("There are no more people infected, you lose..");

    final String explanation;

    EndCauses(final String explanation) {
        this.explanation = explanation;
    }

    /**
     * Returns the explanation for the ending of the game.
     * 
     * @return
     *         explanation message
     */
    public String getExplanation() {
        return explanation;
    }
}
