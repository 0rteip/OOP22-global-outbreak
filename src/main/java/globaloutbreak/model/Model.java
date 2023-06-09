package globaloutbreak.model;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import globaloutbreak.model.cure.Cure;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.events.Event;
import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.voyage.Voyage;

/**
 * Model for GlobalOutbreak app.
 */
public interface Model {

    /**
     * Choosen disease type and name.
     * 
     * @param disease
     *                disease's type
     * @param name
     *                disease's name
     */
    void chosenDisease(Disease disease, String name);

    /**
     * @param disease
     */
    void setDisease(Disease disease);

    /**
     * 
     * @param name
     */
    void setDiseaseName(String name);

        /**
     * Set the {@link Cure} to use.
     * 
     * @param cure
     *             cure
     */
    void setCure(Cure cure);

    /**
     * Move focus on the selected Region.
     * 
     * @param region
     *               Region selected
     */
    void selectedRegion(Region region);

    /**
     * Pass the selected mutation.
     * 
     * @param mutation
     *                 mutation selected
     * @return
     *         {@code True} if is active, {@code False} otherwise
     */
    boolean selectedMutation(String mutation);

    /**
     * List of all the possible Regions.
     * 
     * @return
     *         list of Regions
     */
    List<Region> getRegions();

    /**
     * List of all the possible Diseases.
     * 
     * @return
     *         list of Diseases
     */
    List<Disease> getDiseases();

    /**
     * Get the InfoData contains the info on the current focussed Region.
     * 
     * @return
     *         InfoData of the current focussed Region.
     *         If no region is selected it returns some global Infodata
     */
    InfoData getInfo();

    /**
     * Get rilevant data on the global situations.
     * 
     * @return
     *         global data
     */
    List<Integer> getGlobalData();

    /**
     * 
     * @return
     *         the selected region if is empty is all world
     */
    Optional<Region> getSelectedRegion();

    /**
     * This method creates the class Voyage.
     * 
     * @param sizeAndNameOfMeans
     */
    void createVoyage(Map<String, Pair<Integer, Integer>> sizeAndNameOfMeans);

    /**
     * 
     * @return
     *         class Voyage
     */
    Voyage getVoyage();

    /**
     * Returns {@code True} if game is over.
     * 
     * @return
     *         gameOver
     */
    boolean isGameOver();

    /**
     * Add a listener for the News.
     * 
     * @param listener
     *                 listener
     */
    void addNesListener(PropertyChangeListener listener);
    /**
     * This method extract voyages.
     */
    void extractVoyages();
    /**
     * 
     * @param newdeath
     *                  new death
     * @param region
     *              region
     */
    void incDeathPeople(int newdeath, Region region);
    /**
     * 
     * @param newinfected
     *                      new infected
     * @param region
     *                  region
     */
    void incOrDecInfectedPeople(int newinfected, Region region);
    /**
     * This method cause a event
     */
    void causeEvent();
    /**
     * This method create CauseEvent class
     */
    void createCauseEvents();
    /**
     * 
     * @param regions
     *                  regions
     */
    void setRegions(List<Region> regions);
    /**
     * 
     * @param events
     *              list of Event
     */
    void setEvents(List<Event> events);
    /**
     * 
     * @return
     *          a copy of event's list
     */
    List<Event> getEvents();

    /**
     * update all info.
     */
    void updateInfoData();
}
