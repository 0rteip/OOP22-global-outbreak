package globaloutbreak.model.disease;

import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import globaloutbreak.model.api.Region;

/**
 * Disease factory class.
 */
public class DiseaseFactoryImpl implements DiseaseFactory {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Method to create new Disease.
     * 
     * @param diseaseName
     * @param diseaseType
     * @param diseaseInfectivity
     * @param diseaseLethality
     * @param diseaseAirInfectivity
     * @param diseaseSeaInfectivity
     * @param diseaseLandInfectivity
     * @param diseaseHeatInfectivity
     * @param diseaseColdInfectivity
     * @param diseaseCureResistance
     * @param diseaseHumidityInfectivity
     * @param diseaseAridityInfectivity
     * @param diseasePovertyInfectivity
     * 
     * @return
     *         new {@link Disease}
     */
    @Override
    public Disease createDisease(final String diseaseName, final String diseaseType, final float diseaseInfectivity,
            final float diseaseLethality, final float diseaseAirInfectivity,
            final float diseaseSeaInfectivity, final float diseaseLandInfectivity,
            final float diseaseHeatInfectivity, final float diseaseColdInfectivity, final float diseaseCureResistance,
            final float diseaseHumidityInfectivity, final float diseaseAridityInfectivity,
            final float diseasePovertyInfectivity) {

        if (this.checkIfValid(diseaseInfectivity, "Infectivity") && this.checkIfValid(diseaseLethality, "Lethality")
                && this.checkIfValid(diseaseAirInfectivity, "AirInfectivity")
                && this.checkIfValid(diseaseSeaInfectivity, "SeaInfectivity")
                && this.checkIfValid(diseaseLandInfectivity, "LandInfectivity")
                && this.checkIfValid(diseaseHeatInfectivity, "HeatInfectivity")
                && this.checkIfValid(diseaseColdInfectivity, "ColdInfectivity")
                && this.checkIfValid(diseaseHumidityInfectivity, "HumidityInfectivity")
                && this.checkIfValid(diseaseAridityInfectivity, "AridityInfectivity")
                && this.checkIfValid(diseasePovertyInfectivity, "PovertyInfectivity")
                && this.checkIfValid(diseaseCureResistance, "CureResistance")) {

            return new Disease() {

                private String name = diseaseName;
                private String type = diseaseType;
                private float infectivity = diseaseInfectivity;
                private float lethality = diseaseLethality;
                private float airInfectivity = diseaseAirInfectivity;
                private float landInfectivity = diseaseLandInfectivity;
                private float seaInfectivity = diseaseSeaInfectivity;
                private float heatInfectivity = diseaseHeatInfectivity;
                private float coldInfectivity = diseaseColdInfectivity;
                private float cureResistance = diseaseCureResistance;
                private float humidityInfectivity = diseaseHumidityInfectivity;
                private float aridityInfectivity = diseaseAridityInfectivity;
                private float povertyInfectivity = diseasePovertyInfectivity;
                private Map<String, PropertyChangeSupport> observers = new HashMap<>();

                /**
                 * @return
                 *         the Disease name.
                 */
                @Override
                public String getName() {
                    return this.name;
                }

                /**
                 * @return
                 *         the Disease type
                 */
                @Override
                public String getType() {
                    return this.type;
                }

                /**
                 * @return
                 *         the Disease infectivity.
                 */
                @Override
                public float getInfectivity() {
                    return this.infectivity;
                }

                /**
                 * @return
                 *         the Disease lethality.
                 */
                @Override
                public float getLethality() {
                    return this.lethality;
                }

                /**
                 * @return
                 *         the Disease airTransmission.
                 */
                @Override
                public float getAirInfectivity() {
                    return this.airInfectivity;
                }

                /**
                 * @return
                 *         the Disease seaTransmission.
                 */
                @Override
                public float getSeaInfectivity() {
                    return this.seaInfectivity;
                }

                /**
                 * @return
                 *         the Disease landTransmission.
                 */
                @Override
                public float getLandInfectivity() {
                    return this.landInfectivity;
                }

                /**
                 * @return
                 *         the Disease coldTransmission.
                 */
                @Override
                public float getColdInfectivity() {
                    return this.coldInfectivity;
                }

                /**
                 * @return
                 *         the Disease heatTransmission.
                 */
                @Override
                public float getHeatInfectivity() {
                    return this.heatInfectivity;
                }

                /**
                 * @return
                 *         the Disease cureResistance.
                 */
                @Override
                public float getCureResistance() {
                    return this.cureResistance;
                }

                /**
                 * @return
                 *         the Disease aridityResistance.
                 */
                @Override
                public float getAridityInfectivity() {
                    return this.aridityInfectivity;
                }

                /**
                 * @return
                 *         the Disease humidityResistance.
                 */
                @Override
                public float getHumidityInfectivity() {
                    return this.humidityInfectivity;
                }

                /**
                 * @return
                 *         the Disease povertyTransmission.
                 */
                @Override
                public float getPovertyInfectivity() {
                    return this.povertyInfectivity;
                }

                /**
                 * increase or decrease infectivity.
                 */
                @Override
                public void updateInfectivity(final float infectivity) {
                    if (checkIfValid(this.infectivity + infectivity, diseaseName)) {
                        this.infectivity += infectivity;
                    }
                }

                /**
                 * increase or decrease lethality.
                 */
                @Override
                public void updateLethality(final float lethality) {
                    if (checkParameterUpdate(this.lethality + lethality, "lethality")) {
                        this.lethality += lethality;
                    }
                }

                /**
                 * increase or decrease airTransmission.
                 */
                @Override
                public void updateAirInfectivity(final float airInfectivity) {
                    if (checkParameterUpdate(this.airInfectivity + airInfectivity, "airInfectivity")) {
                        this.airInfectivity += airInfectivity;
                    }
                }

                /**
                 * increase or decrease sea transmission.
                 */
                @Override
                public void updateSeaInfectivity(final float seaInfectivity) {
                    if (checkParameterUpdate(this.seaInfectivity + seaInfectivity, "seaInfectivity")) {
                        this.seaInfectivity += seaInfectivity;
                    }
                }

                /**
                 * increase or decrease land transmission.
                 */
                @Override
                public void updateLandInfectivity(final float landTransmission) {
                    if (checkParameterUpdate(this.landInfectivity + landInfectivity, "landInfectivity")) {
                        this.landInfectivity += landInfectivity;
                    }
                }

                /**
                 * increase or decrease heat transmission.
                 */
                @Override
                public void updateHeatInfectivity(final float heatInfectivity) {
                    if (checkParameterUpdate(this.heatInfectivity + heatInfectivity, "heatInfectivity")) {
                        this.heatInfectivity += heatInfectivity;
                    }
                }

                /**
                 * increase or decrease cold transmission.
                 */
                @Override
                public void updateColdInfectivity(final float coldInfectivity) {
                    if (checkParameterUpdate(this.coldInfectivity + coldInfectivity, "coldInfectivity")) {
                        this.coldInfectivity += coldInfectivity;
                    }
                }

                /**
                 * increase or decrease cure resistance.
                 */
                @Override
                public void updateCureResistance(final float cureResistance) {
                    if (checkParameterUpdate(this.cureResistance + cureResistance, "cureResistance")) {
                        this.cureResistance += cureResistance;
                    }
                }

                /**
                 * increase or decrease aridity resistance.
                 */
                @Override
                public void updateAridityInfectivity(final float aridityInfectivity) {
                    if (checkParameterUpdate(this.aridityInfectivity + aridityInfectivity, "aridityInfectivity")) {
                        this.aridityInfectivity += aridityInfectivity;
                    }
                }

                /**
                 * increase or decrease humidity resistance.
                 */
                @Override
                public void updateHumidityInfectivity(final float humidityInfectivity) {
                    if (checkParameterUpdate(this.humidityInfectivity + humidityInfectivity, "humidityInfectivity")) {
                        this.humidityInfectivity += humidityInfectivity;
                    }
                }

                /**
                 * increase or decrease poverty infectivity.
                 */
                @Override
                public void updatePovertyInfectivity(final float povertyInfectivity) {
                    if (checkParameterUpdate(this.povertyInfectivity + povertyInfectivity, "povertyInfectivity")) {
                        this.povertyInfectivity += povertyInfectivity;
                    }
                }

                /**
                 * Add a new observer to class.
                 */
                @Override
                public void addObserver(final String name, final PropertyChangeSupport subscriber) {
                    observers.put(name, subscriber);
                }

                /**
                 * kill regions people.
                 * 
                 * @param regionList List of all regions
                 */
                @Override
                public void killPeopleRegions(final List<Region> regionList) {

                    regionList.stream()
                            .filter(region -> region.getNumInfected() > 0)
                            .forEach(region -> {
                                region.incDeathPeople(this.calculateNewDeaths(region.getNumInfected()));
                                region.incOrDecNuminfected(-this.calculateNewDeaths(region.getNumInfected()));
                            });
                }

                /**
                 * infect regions people.
                 * 
                 * @param regionList List of all regions
                 */
                @Override
                public void infectRegions(final List<Region> regionList) {
                    regionList.stream()
                            .filter(region -> region.getNumInfected() > 0)
                            .forEach(region -> region.incOrDecNuminfected(calculateNewInfected(region.getPopTot(),
                                    region.getNumInfected(), region.getUrban(), region.getPoor())));
                }

                /**
                 * calculate number of new infected.
                 * 
                 * @param population
                 * @param currentInfected
                 * @param urban
                 * @param poor
                 */
                private int calculateNewInfected(final int population, final int currentInfected, final int urban,
                        final int poor) {
                    if (this.checkIfPositive(population, "population")
                            && this.checkIfPositive(currentInfected, "currentInfected")
                            && this.checkIfPositive(urban, "urban") && this.checkIfPositive(poor, "poor")) {

                        return (int) Math.round(population * ((float) currentInfected / population)
                                * this.calculateInfectivity(urban, poor));
                    }
                    logger.error("The number of population, currentInfected, urban, poor must be at least");
                    return 0;
                }

                private float calculateInfectivity(final int urban, final int poor) {
                    return (this.infectivity + this.landInfectivity * urban
                            + this.povertyInfectivity * poor)
                            / 100;
                }

                /**
                 * calculate number of new deaths.
                 * 
                 * @param infected
                 */
                private int calculateNewDeaths(final int infected) {
                    if (this.checkIfPositive(infected, "infected")) {
                        return (int) Math.round(infected * this.getLethality());
                    }
                    logger.error("The number of infected must be at least 1");
                    return -1;
                }

                /**
                 * Check positive values.
                 * 
                 * @param number
                 * @param name
                 * @return
                 *         true if number is positive, false otherwise
                 */
                private boolean checkIfPositive(final int number, final String name) {
                    if (number < 1) {
                        logger.warn("Value {} can't be negative", name);
                        return false;
                    }
                    return true;
                }

                /**
                 * 
                 * @param value
                 * @param name
                 * @return
                 *         true if number is between 0 and 1, false otherwise
                 * 
                 */
                private boolean checkParameterUpdate(final float value, final String name) {
                    if (value < 0 || value > 1) {
                        logger.error("Error parameter update: The new value of {} is less than 0 or exceeds 1", name);
                        return false;
                    }
                    return true;
                }

            };
        } else {
            logger.error("Errore nella creazione di Disease: I parametri di Disease devono essere compresi tra 0 e 1");
            throw new IllegalArgumentException("I parametri di Disease devono essere compresi tra 0 e 1");
        }
    }

    /**
     * 
     * @param number
     * @param name
     * @return
     *         true if number is between 0 and 1, false otherwise
     */
    private boolean checkIfValid(final float number, final String name) {
        if (number < 0 || number > 1) {
            logger.warn("Value {} must be between 0 and 1", name);
            return false;
        }
        return true;
    }
}
