package globaloutbreak.model;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Optional;

import globaloutbreak.model.endcauses.EndCauses;
import globaloutbreak.model.cure.Cure;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.events.Event;
import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.voyage.Voyage;
import globaloutbreak.model.voyage.Voyages;

/**
 * Model for GlobalOutbreak app.
 */
public interface Model {

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
     * 
     * @return
     *         Disease
     */
    Disease getDisease();

    /**
     * Returns {@code True} id {@code setDisease(Disease disease)} was called.
     * 
     * @return
     *         true if setted
     */
    boolean isDiseaseSet();

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
     *               Region selected (empty = Mondo)
     */
    void selectedRegion(Optional<Region> region);

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
     * Get the InfoData contains the info on the current focussed Region.
     * 
     * @return
     *         InfoData of the current focussed Region.
     *         If no region is selected it returns some global Infodata
     */
    InfoData getInfo();

    /**
     * 
     * @return
     *         the selected region if is empty is all world
     */
    Optional<Region> getSelectedRegion();

    /**
     * 
     * @return
     *         class Voyage
     */
    Voyages getVoyage();

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
    void addListener(PropertyChangeListener listener);

    /**
     * 
     * @param regions
     *                regions
     */
    void setRegions(List<Region> regions);

    /**
     * 
     * @param events
     *               list of Event
     */
    void setEvents(List<Event> events);

    /**
     * Set voyages variable.
     * 
     * @param voyages
     */
    void setVoyages(Voyages voyages);

    /**
     * Returns the voyages.
     * 
     * @return
     *         list of {@link Voyage}
     */
    List<Voyage> getVoyages();

    /**
     * Update {@link Model}.
     */
    void update();

    /**
     * Returns the {@link EndCauses} for the game if game has ended.
     * 
     * @return
     *         optional od endcauses
     */
    Optional<EndCauses> getEndCause();

}
