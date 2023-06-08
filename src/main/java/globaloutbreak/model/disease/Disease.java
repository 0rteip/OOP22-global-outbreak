package globaloutbreak.model.disease;

import java.beans.PropertyChangeListener;
import java.util.List;

import globaloutbreak.model.region.Region;


/**
 * Interface of disease.
 */
public interface Disease {

    /**
     * 
     * @return
     *         disease name.
     */
    String getName();

    /**
     * 
     * @return
     *         the disease type.
     */
    String getType();

    /**
     * 
     * @return
     *         disease infectivity.
     */
    float getInfectivity();

    /**
     * 
     * @return
     *         disease lethality.
     */
    float getLethality();

    /**
     * 
     * @return
     *         disease air infectivity.
     */
    float getAirInfectivity();

    /**
     * 
     * @return
     *         disease sea infectivity
     */
    float getSeaInfectivity();

    /**
     * 
     * @return
     *         disease land infectivity
     */
    float getLandInfectivity();

    /**
     * 
     * @return
     *         disease cold infectivity
     */
    float getColdInfectivity();

    /**
     * 
     * @return
     *         disease heat infectivity
     */
    float getHeatInfectivity();

    /**
     * 
     * @return
     *         disease aridity resistance
     */
    float getAridityInfectivity();

    /**
     * 
     * @return
     *         disease humidity resistance
     */
    float getHumidityInfectivity();

    /**
     * 
     * @return
     *         disease cure resistance
     */
    float getCureResistance();

    /**
     * 
     * @return
     *         disease poverty infectivity
     */
    float getPovertyInfectivity();

    /**
     * Set the Disease name.
     * 
     * @param name
     *         disease name
     */
    void setName(String name);

    /**
     * 
     * @param infectivity
     */
    void updateInfectivity(float infectivity);

    /**
     * 
     * @param lethality
     */
    void updateLethality(float lethality);

    /**
     * 
     * @param airInfectivity
     */
    void updateAirInfectivity(float airInfectivity);

    /**
     * 
     * @param seaInfectivity
     */
    void updateSeaInfectivity(float seaInfectivity);

    /**
     * 
     * @param landInfectivity
     */
    void updateLandInfectivity(float landInfectivity);

    /**
     * 
     * @param heatInfectivity
     */
    void updateHeatInfectivity(float heatInfectivity);

    /**
     * 
     * @param coldInfectivity
     */
    void updateColdInfectivity(float coldInfectivity);

    /**
     * 
     * @param aridityInfectivity
     */
    void updateAridityInfectivity(float aridityInfectivity);

    /**
     * 
     * @param humidityInfectivity
     */
    void updateHumidityInfectivity(float humidityInfectivity);

    /**
     * 
     * @param cureResistance
     */
    void updateCureResistance(float cureResistance);

    /**
     * 
     * @param povertyInfectivity
     */
    void updatePovertyInfectivity(float povertyInfectivity);

    /**
     * infect region population.
     * 
     * @param regionList
     */
    void infectRegions(List<Region> regionList);

    /**
     * kill region population.
     * 
     * @param regionList
     */
    void killPeopleRegions(List<Region> regionList);

    /**
     * Initialize Observers.
     * 
     * @param name
     * 
     * @param listener
     * 
     */
    void initializeObserver(String name, PropertyChangeListener listener);

    /**
     * 
     * @return
     *         all Disease data as String.
     */
    @Override
    String toString();
}
