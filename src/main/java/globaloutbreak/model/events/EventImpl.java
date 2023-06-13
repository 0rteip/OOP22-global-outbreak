package globaloutbreak.model.events;

/**
 * Implement. of EventInt.
 */
public final class EventImpl implements Event {
    private final float probOfHapp;
    private final float percOfDeath;
    private final String name;

    /**
     * Constractor.
     * 
     * @param name
     *                    region's name
     * @param probOfHapp
     *                    likelihood it could happen
     * @param percOfDeath
     *                    percentage of deaths in the total population
     */
    public EventImpl(final String name, final float probOfHapp, final float percOfDeath) {
        this.name = name;
        this.probOfHapp = probOfHapp;
        this.percOfDeath = percOfDeath;
    }

    @Override
    public float getProbOfHapp() {
        return this.probOfHapp;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public float getPercOfDeath() {
        return percOfDeath;
    }

}
