package globaloutbreak.controller;

import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.infodata.InfoData;
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
    void updateInfo();

    /**
     * Display info in general charts.
     */
    void displayInfo();
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
}
