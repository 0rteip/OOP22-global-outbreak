package globaloutbreak.model.events;


/**
 * Implement. of EventInt.
 */
public final class Event implements EventInt {
    private final float probOfHapp;
    private final float percOfDeath;
    private final String name;
    /**
     * Constractor.
     * @param name 
     *              region's name
     * @param probOfHapp
     *                  likelihood it could happen
     * @param percOfDeath
     *                      percentage of deaths in the total population
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
