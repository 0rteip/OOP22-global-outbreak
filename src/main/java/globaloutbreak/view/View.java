package globaloutbreak.view;

import java.util.List;

import globaloutbreak.controller.api.Controller;
import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.api.Message;
import globaloutbreak.model.api.Voyage;
import globaloutbreak.model.disease.DiseaseData;
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
     * Returns the {@link Controller}.
     * 
     * @return
     *         Controller
     */
    Controller getController();

    /**
     * Returns the {@link SceneManager}.
     * 
     * @return
     *         SceneManager
     */
    SceneManager getSceneManager();

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
}
