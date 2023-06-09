package globaloutbreak.model.events;

/**
 * 
 */
public final class Event implements EventInt {
    private float probOfHapp;
    private float percOfDeath;
    private final String name;

    /**
     * 
     * @param list
     *                    list
     * @param probOfHapp
     *                    probability
     * @param percOfDeath
     *                    percentage
     */
    public Event(final String name, final float probOfHapp, final float percOfDeath) {
        this.name = name;
        this.probOfHapp = probOfHapp;
        this.percOfDeath = percOfDeath;
    }

    @Override
    public Integer calcDeath(final Integer popTot) {
        return (int) Math.floor(popTot * percOfDeath);
    }

    @Override
    public float getProbOfHapp() {
        return this.probOfHapp;
    }

    @Override
    public String getName() {
        return name;
    }
}
