package globaloutbreak.model.api;

/**
 * 
 */
public interface Disease {
    float getInfectivity();

    float getLethality();

    float getAirTransmission();

    float getSeaTransmission();

    float getLandTransmission();

    float getColdTransmission();

    float getHotTransmission();

    float getCureResistance();

    void setInfectivity();

    void setLethality();

    void setAirTransmission();

    void setSeaTransmission();

    void setLandTransmission();

    void setHotTransmission();

    void setColdTransmission();

    void setCureResistance();

    void infect();

    void kill();
}
