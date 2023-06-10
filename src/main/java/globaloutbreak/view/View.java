package globaloutbreak.view;

import java.util.List;
import java.util.Map;

import globaloutbreak.controller.Controller;
import globaloutbreak.controller.TypeOfInfo;
import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.disease.DiseaseData;
import globaloutbreak.model.voyage.Voyage;
import globaloutbreak.settings.gamesettings.GameSettingsGetter;
import globaloutbreak.settings.windowsettings.WindowSettings;
import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.view.scenemanager.SceneManager;
import javafx.scene.control.Button;

/**
 * interface View.
 */
public interface View {

    List<DiseaseData> getDiseasesDatas();
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
     * @return
     *         infodata
     */
    InfoData getInfoData();

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
     * Returns the GameSettings.
     * 
     * @return
     *         {@link GameSettingsGetter}
     */
    GameSettingsGetter getGameSettings();

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

    /**
     * 
     * @return
     *         info of selected region
     */
    Map<TypeOfInfo, String> getInfoSingleRegion();

    /**
     * 
     * @param color
     */
    void selectRegion(int color);

}
