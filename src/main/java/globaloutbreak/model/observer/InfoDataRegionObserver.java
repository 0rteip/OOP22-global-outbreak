package globaloutbreak.model.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import globaloutbreak.model.infodata.InfoData;

/**
 * Observer to observe region class for new regions infected.
 */
public class InfoDataRegionObserver implements PropertyChangeListener {

    private final InfoData infoData;
    private final Random random = new Random();

    /**
     * Create a region observer that refers to a {@link InfoData}.
     * 
     * @param infodata
     *                 notified
     */
    public InfoDataRegionObserver(final InfoData infodata) {
        this.infoData = infodata;
    }

    /**
     * @param property
     *                 the property that changed value.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent property) {
        if (property.getPropertyName().equals("infectedRegion")
                && (int) property.getNewValue() > (int) property.getOldValue() && (int) property.getOldValue() == 0) {
            this.infoData.increasePoints(random.nextInt(2) + 1);
        }
    }

}
