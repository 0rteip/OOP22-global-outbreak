package globaloutbreak.view;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import globaloutbreak.controller.Controller;
import globaloutbreak.controller.TypeOfInfo;
import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.disease.DiseaseData;
import globaloutbreak.model.voyage.Voyages;
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
    void displayVoyage(Voyages voyage);

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
     * @return list of mutation name
     *         
     */
    List<String> getMutations();

    /**
     * 
     * @param diseasesNames
     *                      the list of diseases names
     */
    void setDiseasesData(List<DiseaseData> diseasesNames);
    /**
     * get points.
     */
    String getPoints();
    /**
     * 
     * @param mutationsNames the list of mutation names
     * @param points points
     *                       
     */
    void setMutationsName(List<String> mutationsNames, int points);
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
     * 
     * @param desc description 
     * @param activate if the mutation is acrive
     * @param  cost cost of the mutation
     */
    void setMutationsDesc(String desc, Boolean activate, int cost);

     /**
     *
     * @return description mutation
     *
    */
    String  getDescription();

    /**
     * 
     * @return if the mutation is active
     */
    boolean checkactivate();

    /**
    * 
    *@return cost of mutation
    */
    String getCost();

    /**
     * 
     */
    void quit();

    /**
     * method to display mutation.
     */
    void displayMutation();

    /**
     * method to display mutation description.
     * @param name name of the mutation
     */
    void displayMutationDesc(String name);

    /**
     * method for update the disease.
     * @param name  name of the mutation
     */
    void update(String name);

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
    void selectRegion(Optional<Integer> color);

}
