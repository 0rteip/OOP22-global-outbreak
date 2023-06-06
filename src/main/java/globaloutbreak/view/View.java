package globaloutbreak.view;

import java.util.List;

import globaloutbreak.controller.Controller;
import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.api.Voyage;
import globaloutbreak.settings.windowsettings.WindowSettings;
import globaloutbreak.view.scenefactory.SceneAdministrator;

/**
 * interface View.
 */
public interface View extends Cloneable {

    /**
     * Clones the View.
     * 
     * @return
     *         aview clone
     */
    View clone();

    /**
     * Start the View.
     * 
     * @param controller
     *                   controller
     */
    void start(Controller controller);

    /**
     * Update visual info.
     * 
     * @param info
     *             to update
     */
    void displayInfo(Infodata info);

    /**
     * Display the message notification.
     * 
     * @param message
     *                to display
     */
    void displayMessage(Message message);

    /**
     * Display the Voyage.
     * 
     * @param voyage
     *               voyage to display
     */
    void displayVoyage(Voyage voyage);

    /**
     * Returns the {@link WindowSettings}.
     * 
     * @return
     *         WindowSettings
     */
    WindowSettings getWindowSettings();

    /**
     * Returns the {@link SceneAdministrator}.
     * 
     * @return
     *         SceneAdministrator
     */
    SceneAdministrator getSceneAdministrator();

    /**
     * Returns True if game is running.
     * 
     * @return
     *         game running
     */
    boolean isGameRunning();

    /**
     * Start or stop the game.
     */
    void startStop();

    /**
     * Returns the current gameSpeed.
     * 
     * @return
     *         GameSpeed
     */
    GameSpeed getGameSpeed();

    /**
     * Returns the GameSpeed s.
     * 
     * @return
     *         List of gameSPeed
     */
    List<GameSpeed> getGameSpeeds();

    /**
     * Set the current GameSpeed.
     * 
     * @param gameSpeed
     *                  GameSpeed
     */
    void setGameSpeed(GameSpeed gameSpeed);

}
