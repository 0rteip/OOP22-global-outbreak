package globaloutbreak.gamespeed;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
    public GameSpeedObserver(final View view) {
        this.view = view;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent arg0) {
        this.view.setGameSpeed((GameSpeed) arg0.getNewValue());
    }

}
