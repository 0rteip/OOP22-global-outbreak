package globaloutbreak.model.events;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Implement. of EventInt.
 */
public final class EventImpl implements Event {
    private final float probOfHapp;
    private final float percOfDeath;
    private final String name;
    private PropertyChangeSupport infodataSupport = new PropertyChangeSupport(this);
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
    public long calcDeath(final long popTot) {
        infodataSupport.firePropertyChange("eventdeath", 0, (int) Math.floor(popTot * percOfDeath));
        return (long) Math.floor(popTot * percOfDeath);
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
    public void initializeObserver(PropertyChangeListener listener){
        this.infodataSupport.addPropertyChangeListener(listener);
    }
}
