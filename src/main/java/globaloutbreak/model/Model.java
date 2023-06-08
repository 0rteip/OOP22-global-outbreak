package globaloutbreak.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.cure.Cure;
import globaloutbreak.model.disease.Disease;
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
    void addRegion(Integer popTot, String name, Map<String,Pair<Integer,Optional<List<String>>>> reachableRegion, float urban, float poor,
        Integer color, Integer facilities, float hot, float humid);

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
    public Optional<Region> getSelectedRegion();

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
    * This method add a new event.

    * @param death
                    perc. of death
    * @param name
                    event's name 
    * @param prob
                    perc. of prob.
    */
    void addEvent(float death, String name, float prob);

     /**
     * Returns {@code True} if game is over.
     * 
     * @return
     *         gameOver
     */
    boolean isGameOver();
}
