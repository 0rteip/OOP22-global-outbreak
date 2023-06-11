package globaloutbreak.model.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import globaloutbreak.model.infodata.InfoData;

/**
 * Observer to observe region class for new regions infected.
 */
public class InfoDataEventObserver implements PropertyChangeListener {

    private final InfoData infoData;

    /**
     * Create a region observer that refers to a {@link InfoData}.
     * 
     * @param infodata
     *                 notified
     */
    public InfoDataEventObserver(final InfoData infodata) {
        this.infoData = infodata;
    }

    /**
     * @param property
     *                 the property that changed value.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent property) {
        if ("eventdeath".equals(property.getPropertyName())) {
            this.infoData.updateDeaths((long) property.getNewValue());
        }
    }

}

