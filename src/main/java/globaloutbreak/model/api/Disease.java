package globaloutbreak.model.api;

import java.util.List;

/**
 * Interface of disease.
 */
public interface Disease {

    /**
     * 
     * @return
     *         disease name
     */
    String getname();

    /**
     * 
     * @return
     *         disease infectivity
     */
    float getInfectivity();

    /**
     * 
     * @return
     *         disease lethality
     */
    float getLethality();

    /**
     * 
     * @return
     *         disease air transmission
     */
    float getAirTransmission();

    /**
     * 
     * @return
     *         disease sea transmission
     */
    float getSeaTransmission();

    /**
     * 
     * @return
     *         disease land transmission
     */
    float getLandTransmission();

    /**
     * 
     * @return
     *         disease cold transmission
     */
    float getColdTransmission();

    /**
     * 
     * @return
     *         disease heat transmission
     */
    float getHeatTransmission();

    /**
     * 
     * @return
     *         disease cold transmission
     */
    float getAridityResistance();

    /**
     * 
     * @return
     *         disease humidity resistance
     */
    float getHumidityResistance();

    /**
     * 
     * @return
     *         disease cure resistance
     */
    float getCureResistance();

    /**
     * 
     * @return
     *         disease poverty transmission
     */
    float getPovertyTransmission();

    /**
     * 
     * @param infectivity
     */
    void setInfectivity(float infectivity);

    /**
     * 
     * @param lethality
     */
    void setLethality(float lethality);

    /**
     * 
     * @param airTransmission
     */
    void setAirTransmission(float airTransmission);

    /**
     * 
     * @param seaTransmission
     */
    void setSeaTransmission(float seaTransmission);

    /**
     * 
     * @param landTransmission
     */
    void setLandTransmission(float landTransmission);

    /**
     * 
     * @param heatTransmission
     */
    void setHeatTransmission(float heatTransmission);

    /**
     * 
     * @param coldTransmission
     */
    void setColdTransmission(float coldTransmission);

    /**
     * 
     * @param aridityResistance
     */
    void setAridityResistance(float aridityResistance);

    /**
     * 
     * @param humidityResistance
     */
    void setHumidityResistance(float humidityResistance);

    /**
     * 
     * @param cureResistance
     */
    void setCureResistance(float cureResistance);

    /**
     * 
     * @param povertyTransmission
     */
    void setPovertyTransmission(float povertyTransmission);

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
}
