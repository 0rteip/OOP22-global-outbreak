package globaloutbreak.model.impl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import globaloutbreak.model.api.Cure;

/**
 * An observer which notify the {@link Cure} if {@link globaloutbreak.model.api.Disease} mutations
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
        if ("difficulty".equals(property.getPropertyName())) {
            this.cure.increaseResearchDifficulty((float) property.getNewValue());
        } else if ("progress".equals(property.getPropertyName())) {
            this.cure.reduceResearchProgress((float) property.getNewValue());
        }
    }
}
