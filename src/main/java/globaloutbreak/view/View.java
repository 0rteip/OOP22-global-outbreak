package globaloutbreak.view;

import java.util.List;

import globaloutbreak.controller.Controller;
import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.disease.DiseaseData;
import globaloutbreak.model.voyage.Voyage;
import globaloutbreak.model.infodata.Infodata;
import globaloutbreak.view.scenemanager.SceneManager;
import javafx.scene.control.Button;
import settings.WindowSettings;

/**
 * interface View.
 */
public interface View {

    /**
     * Start the view.
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
     * Returns the {@link SceneManager}.
     * 
     * @return
     *         SceneManager
     */
    SceneManager getSceneManager();

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

    /**
     * 
     * @return
     *         List<Button> diseasesButtons
     */
    List<Button> getDiseasesButtons();

    /**
     * 
     * @param diseasesNames
     *                      the list of diseases names
     */
    void setDiseasesData(List<DiseaseData> diseasesNames);

    /**
     * 
     * @param type
     */
    void choosenDisease(String type);

    /**
     * 
     * @param name
     */
    void choosenNameDisease(String name);

    /**
     * Quit Application.
     */
    void quit();
}
