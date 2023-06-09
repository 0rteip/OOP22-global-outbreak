package globaloutbreak.gamespeed;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import globaloutbreak.view.View;

/**
 * Game Speed observer.
 */
public final class GameSpeedObserver implements PropertyChangeListener {

    private final View view;

    /**
     * Create an observer.
     * 
     * @param view
     *             the main view
     */
    // @formatter:off
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "We need to use the correct instance of View"
    )
    // @formatter:on
    public GameSpeedObserver(final View view) {
        this.view = view;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent arg0) {
        this.view.setGameSpeed((GameSpeed) arg0.getNewValue());
    }

}
