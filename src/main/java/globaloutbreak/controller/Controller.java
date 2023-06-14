package globaloutbreak.controller;

import java.util.Map;
import java.util.List;
import java.util.Optional;

import globaloutbreak.controller.region.TypeOfInfo;
import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.mutation.Mutation;
import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.message.Message;
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
     * 
     * @return
     * disease
     */
    Disease getDisease();
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
     * @param increase increase of the mutation
     */
    void setMutationsDesc(String description, boolean activate, int cost, float increase);

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
    /**
     * display points.
     */
    void displayPoints();
    /**
     * 
     * @return
     *          the name of means
     */
    List<String> getMeans();

}
