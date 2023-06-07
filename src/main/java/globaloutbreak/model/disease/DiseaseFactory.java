package globaloutbreak.model.disease;

/**
 * Interface that models the disease creator.
 */
public interface DiseaseFactory {

    /**
     * 
     * @param type
     * @param infectivity
     * @param lethality
     * @param airTransmission
     * @param seaTransmission
     * @param landTransmission
     * @param heatTransmission
     * @param coldTransmission
     * @param cureResistance
     * @param humidityResistance
     * @param aridityResistance
     * @param povertyTransmission
     * 
     * @return
     *         new Disease
     */
    Disease createDisease(String type, float infectivity, float lethality, float airTransmission,
            float seaTransmission, float landTransmission,
            float heatTransmission, float coldTransmission, float cureResistance,
            float humidityResistance, float aridityResistance, float povertyTransmission);

}
