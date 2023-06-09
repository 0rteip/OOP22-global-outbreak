package globaloutbreak.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.events.Event;
import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.voyage.Voyage;

/**
 * Model for GlobalOutbreak app.
 */
public interface Model {

    /**
     * Create a new region.
     * 
     * @param popTot 
     *              the total population 
     * @param name
     *              the name of the region
     * @param reachableRegion
     *                        means with the list of reachable state and the numbre of the mean
     * @param urban
     *              percentage of people living in urban areas
     * @param poor
     *              percentage of people who are below the poverty line
     * @param color
     *              the color of the region
     * @param facilities
     *                  number of care facilities
     * @param hot
     *               percentage of hot climate
     * @param humid
     *              percentage of humidity
     */
    void addRegion(int popTot, String name, Map<String, Pair<Integer, Optional<List<String>>>> reachableRegion, 
            float urban, float poor,
            int color, int facilities, float hot, float humid);

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
    Infodata getInfo();

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
     *          the selected region if is empty is all world
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
     *          class Voyage
     */
    Voyage getVoyage();

    /**
     * Thi method extract voyages.
     * 
     * @return
     *         String is the type of means, Integer is the number of new infected 
     *          and in the pair there is departure and destination expressed with the color of region
     */
    void extractVoyages();
    /**
     * 
     * @param newdeath
     *                  new death
     * @param region
     *                  region
     */
    void incDeathPeople(int newdeath, Region region);
    /**
     * 
     * @param newinfected
     *                      new death
     * @param region
     *                  region
     */
    void incOrDecInfectedPeople(int newinfected, Region region);
    /**
     * This method cause a event.
     * If is Presesent audate deathPeople
     * 
     */
    void causeEvent();
    /**
     * 
     * @return
     *          the list of events
     */
    List<Event> getEvents();
    /**
     * This method creates CauseEvent class.
     */
    void createCauseEvents();
    /**
     * This method set region's the list.
     * 
     * @param regions
     *                  list of regions
     */
    void setRegions(List<Region> regions);
     /**
     * This method set the event's list.
     * 
     * @param events
     *                  list of event
     */
    void setEvents(List<Event> events);
}
