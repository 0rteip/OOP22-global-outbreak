package globaloutbreak.model.cure.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import globaloutbreak.model.cure.Cure;

/**
 * An observer which notify the {@link Cure} if
 * {@link globaloutbreak.model.api.Disease} mutations
 * affect it's research.
 */
public final class DiseaseObserver implements PropertyChangeListener {

    private final Cure cure;

    /**
     * Create a disease observer that refere to a {@link Cure}.
     * 
     * @param cure
     *             notified
     */
    public DiseaseObserver(final Cure cure) {
        this.cure = cure;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent property) {
        switch (property.getPropertyName()) {
            case "resist":
                this.cure.increaseResearchDifficulty((float) property.getNewValue());
                break;
            case "decre":
                this.cure.reduceResearchProgress((float) property.getNewValue());
                break;
            default:
                break;
        }
    }
}
