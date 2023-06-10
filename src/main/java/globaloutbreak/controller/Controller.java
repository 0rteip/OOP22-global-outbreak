package globaloutbreak.controller;

import java.util.List;

import globaloutbreak.gamespeed.GameSpeed;
//import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.mutation.Mutation;
import globaloutbreak.model.infodata.Infodata;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.voyage.Voyage;
import globaloutbreak.settings.gamesettings.GameSettingsGetter;

/**
 * Controller interface.
 */
public interface Controller {

    /**
     * Choosen disease type.
     * 
     * 
     * @param type
     *             disease's type
     */
    void choosenDisease(String type);

    /**
     * Choosen disease name.
     * 
     * 
     * @param name
     *             disease's name
     */
    void choosenDiseaseName(String name);

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
     * Start or stop the game.
     */
    void startStop();

    /**
     * Set the game speed.
     * 
     * @param gameSpeed
     *                  to set
     */
    void setGameSpeed(GameSpeed gameSpeed);

    /**
     * Returns if the Game is running.
     * 
     * @return
     *         {@code True} if is running
     */
    boolean isGameRunning();

    /**
     * Returns the GameSettingsGetter.
     * 
     * @return
     *         GameSettingsGetter
     */
    GameSettingsGetter getSettings();


    /**
     * Creates the Disease.
     * 
     * @param type
     *             tyoe of disease
     */
    void createDisease(String type);

    /**
     * Read Disease.
     */
    void readDiseasesNames();

    /**
     * ask mutation name.
     */
    void displayMutationsName();

   /**
    * set mutation name.
    * @param list list of mutation name
    */
    void setMutationsName(List<String> list);

    /**
     * set mutation information.
     * @param description description of the mutation
     * @param activate {@code True} if is active
     * @param cost cost of the mutation
     */
    void setMutationsDesc(String description, boolean activate, int cost);

    /**
     * displat mutation description.
     * @param name name of the mutation
     */
    void displayMuatationDesc(String name);

    /**
     * update disease.
     * @param name name of the mutation
     */
    void update(String name);
}
