package globaloutbreak.view;

import globaloutbreak.controller.api.Controller;
import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.api.Message;
import globaloutbreak.model.api.Voyage;
import globaloutbreak.view.scenemanager.SceneManager;
import javafx.stage.Stage;
import settings.WindowSettings;

/**
 * interface View.
 */
public interface View {

    /**
     * Start the view.
     */
    void start(Controller controller, Stage stage);

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
     * Returns the {@link SceneAdministrator}.
     * 
     * @return
     *         SceneAdministrator
     */
    SceneManager getSceneManager();
}
