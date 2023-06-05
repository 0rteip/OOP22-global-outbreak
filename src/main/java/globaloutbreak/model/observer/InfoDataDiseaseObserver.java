package globaloutbreak.model.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import globaloutbreak.model.infodata.InfoData;

/**
 * Observer to observe Disease class infect and kill methods.
 */
public class InfoDataDiseaseObserver implements PropertyChangeListener {

    private static final int INFECTED_BASE = 500_000;
    private static final int DEATHS_BASE = 500_000;

    private final Random random = new Random();

    private final InfoData infoData;

    private int infectedMultipleBase;
    private int deathsMultipleBase;

    /**
     * Create a disease observer that refere to a {@link InfoData}.
     * 
     * @param infoData
     *                 notified
     */
    public InfoDataDiseaseObserver(final InfoData infoData) {
        this.infoData = infoData;
        this.infectedMultipleBase = INFECTED_BASE;
        this.deathsMultipleBase = DEATHS_BASE;
    }

    /**
     * @param property
     *                 the property that changed value.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent property) {
        if (property.getPropertyName().equals("infected") && (int) property.getNewValue() > this.infectedMultipleBase) {
            this.infoData.increasePoints(random.nextInt(3) + 1);
            this.infectedMultipleBase += INFECTED_BASE;
        } else if (property.getPropertyName().equals("deaths")
                && (int) property.getNewValue() > this.deathsMultipleBase) {
            this.infoData.increasePoints(random.nextInt(3) + 1);
            this.deathsMultipleBase += DEATHS_BASE;
        }
    }
}
