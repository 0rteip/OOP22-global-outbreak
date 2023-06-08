package globaloutbreak.model;

import java.util.List;

import globaloutbreak.model.api.Region;
import globaloutbreak.model.disease.Disease;

/**
 * Model for GlobalOutbreak app.
 */
public interface Model {

    /**
     * Choosen disease.
     * 
     * @param disease
     *                disease
     */
    void setDisease(Disease disease);

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
     *         Disease
     */
    Disease getDisease();

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
