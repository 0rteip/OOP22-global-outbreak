package globaloutbreak.controller;

import java.util.Map;
import java.util.Optional;

import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.voyage.Voyages;
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
    void selectedRegion(Optional<Integer> region);

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
     */
    void updateInfo();

    /**
     * Display info in general charts.
     * 
     * @return
     *         infodata
     */
    InfoData displayInfo();

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
    void startVoyage(Voyages voyage);

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
     * Read Disease.
     */
    void readDiseasesNames();

    /**
     * This method find Info of selected region.
     * 
     * @return
     *         the ifo
     */
    Map<TypeOfInfo, String> getInfoSingleRegion();
    /**
     * This method set a list of model.
     */
    void setRegions();
}
