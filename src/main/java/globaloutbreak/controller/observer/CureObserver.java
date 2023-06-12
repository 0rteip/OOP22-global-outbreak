package globaloutbreak.controller.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import globaloutbreak.controller.Controller;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.message.MessageType;

/**
 * Cure observer.
 */
public final class CureObserver implements PropertyChangeListener {

    private final Controller controller;

    /**
     * Create an observer.
     * 
     * @param controller
     *                   the main controller
     */
    // @formatter:off
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "We need to use the correct instance of the Controller"
    )
    // @formatter:on
    public CureObserver(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent arg0) {
        if (arg0.getPropertyName().equals(MessageType.CURE.getTitle())) {
            this.controller.displayMessage((Message) arg0.getNewValue());
        }
    }
}
