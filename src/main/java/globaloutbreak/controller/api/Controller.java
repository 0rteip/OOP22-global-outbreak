package globaloutbreak.controller.api;

import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.api.Message;
import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.api.Region;
import globaloutbreak.model.api.Voyage;
import javafx.stage.Stage;

/**
 * Controller interface.
 */
public interface Controller {

    /**
     * Start a game, create model and view.
     * 
     * @param stage
     */
    void startGame(Stage stage);

    /**
     * Choosen disease type and name.
     * 
     * 
     * @param type
     *                disease's type
     */
    void choosenDisease(String type);

    /**
     * Pass the selected region.
     * 
     * @param region
     *               region selected
     */
    void selectedRegion(Region region);

    /**
     * Pass the selected mutation.
     * 
     * @param mutation
     *                 mutation selected
     */
    void selectedMutation(Mutation mutation);

    /**
     * Update DNA points, cure status, general info.
     * 
     * @param info
     *             info to update
     */
    void updateInfo(Infodata info);

    /**
     * Display the message notification.
     * 
     * @param message
     *                message to display
     */
    void displayMessage(Message message);

    /**
     * Start a Voyage.
     * 
     * @param voyage
     *               to start
     */
    void startVoyage(Voyage voyage);

    /**
     * Quits from application.
     */
    void quit();

    /**
     * Read diseas names.
     */
    void readDiseasesNames();
}
