package globaloutbreak.model.api;

/**
 * 
 */
public interface Disease {
    double getInfectivity();

    double getLethality();

    double getAirTransmission();

    double getSeaTransmission();

    double getLandTransmission();

    double getColdTransmission();

    double getHotTransmission();

    double getCureResistance();

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
