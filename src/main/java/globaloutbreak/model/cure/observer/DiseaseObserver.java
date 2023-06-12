package globaloutbreak.model.cure.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import globaloutbreak.model.cure.Cure;

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
    // @formatter:off
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "We need to use the correct instance of the Cure"
    )
    // @formatter:on
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
