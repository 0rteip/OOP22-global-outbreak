package globaloutbreak.model.api;

import java.util.List;

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
     * Get the InfoData contains the info on the current
     * focussed Region.
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

}
