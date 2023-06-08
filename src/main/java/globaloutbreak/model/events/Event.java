package globaloutbreak.model.events;


/**
 * 
 */
public class Event implements EventInt {
    private float probOfHapp;
    private float percOfDeath;
    private final String name;
    /**
     * 
     * @param list
     * @param probOfHapp
     * @param percOfDeath
     */
    public Event(final String name, final float probOfHapp, final float percOfDeath) {
        this.name = name;
        this.probOfHapp = probOfHapp;
        this.percOfDeath = percOfDeath;
    }


    public Integer calcDeath(final Integer popTot) {
        return (int)Math.floor(popTot * percOfDeath);
    }

    public float getProbOfHapp() {
        return this.probOfHapp;
    }

    public String getName() {
        return name;
    }

    
}
