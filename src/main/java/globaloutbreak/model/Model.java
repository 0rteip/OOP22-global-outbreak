package globaloutbreak.model;

import java.util.List;

import globaloutbreak.model.api.Disease;
import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.cure.Cure;
import globaloutbreak.model.region.Region;

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
    boolean selectedMutation(Mutation mutation);

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

    /**
     * Returns {@code True} if game is over.
     * 
     * @return
     *         gameOver
     */
    boolean isGameOver();

}
