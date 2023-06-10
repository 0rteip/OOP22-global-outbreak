package globaloutbreak.model.disease;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import org.slf4j.LoggerFactory;
import globaloutbreak.model.region.Region;
import org.slf4j.Logger;

/**
 * Disease factory class.
 */
public class DiseaseFactoryImpl implements DiseaseFactory {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Disease createDisease(final String diseaseType, final float diseaseInfectivity,
            final float diseaseLethality, final float diseaseAirInfectivity,
            final float diseaseSeaInfectivity, final float diseaseLandInfectivity,
            final float diseaseHeatInfectivity, final float diseaseColdInfectivity, final float diseaseCureResistance,
            final float diseaseHumidityInfectivity, final float diseaseAridityInfectivity,
            final float diseasePovertyInfectivity) {

            return new Disease() {

                private String name;
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
                private PropertyChangeSupport infodataSupport = new PropertyChangeSupport(this);

                @Override
                public String getName() {
                    return this.name;
                }

                @Override
                public String getType() {
                    return this.type;
                }

                @Override
                public float getInfectivity() {
                    return this.infectivity;
                }

                @Override
                public float getLethality() {
                    return this.lethality;
                }

                @Override
                public float getAirInfectivity() {
                    return this.airInfectivity;
                }

                @Override
                public float getSeaInfectivity() {
                    return this.seaInfectivity;
                }

                @Override
                public float getLandInfectivity() {
                    return this.landInfectivity;
                }

                @Override
                public float getColdInfectivity() {
                    return this.coldInfectivity;
                }

                @Override
                public float getHeatInfectivity() {
                    return this.heatInfectivity;
                }

                @Override
                public float getCureResistance() {
                    return this.cureResistance;
                }

                @Override
                public float getAridityInfectivity() {
                    return this.aridityInfectivity;
                }

                @Override
                public float getHumidityInfectivity() {
                    return this.humidityInfectivity;
                }

                @Override
                public float getPovertyInfectivity() {
                    return this.povertyInfectivity;
                }

                @Override
                public void setName(final String diseaseName) {
                    this.name = diseaseName;
                }

                @Override
                public void updateInfectivity(final float infectivity) {
                    this.infectivity = getParameterUpdate(this.infectivity + infectivity, "infectivity");
                }

                @Override
                public void updateLethality(final float lethality) {
                    this.lethality = getParameterUpdate(this.lethality + lethality, "lethality");
                }

                @Override
                public void updateAirInfectivity(final float airInfectivity) {
                    this.airInfectivity = getParameterUpdate(this.airInfectivity + airInfectivity, "airInfectivity");
                }

                @Override
                public void updateSeaInfectivity(final float seaInfectivity) {
                    this.seaInfectivity = getParameterUpdate(this.seaInfectivity + seaInfectivity, "seaInfectivity");
                }

                @Override
                public void updateLandInfectivity(final float landTransmission) {
                    this.landInfectivity = getParameterUpdate(this.landInfectivity + landInfectivity, "landInfectivity");
                }

                @Override
                public void updateHeatInfectivity(final float heatInfectivity) {
                    this.heatInfectivity = getParameterUpdate(this.heatInfectivity + heatInfectivity, "heatInfectivity");
                }

                @Override
                public void updateColdInfectivity(final float coldInfectivity) {
                    this.coldInfectivity = getParameterUpdate(this.coldInfectivity + coldInfectivity, "coldInfectivity");
                }

                @Override
                public void updateCureResistance(final float cureResistance) {
                    this.cureResistance += cureResistance;
                }

                @Override
                public void updateAridityInfectivity(final float aridityInfectivity) {
                    this.aridityInfectivity = getParameterUpdate(this.aridityInfectivity + aridityInfectivity, "aridityInfectivity");
                }

                @Override
                public void updateHumidityInfectivity(final float humidityInfectivity) {
                    this.humidityInfectivity = getParameterUpdate(this.humidityInfectivity + humidityInfectivity, "humidityInfectivity");
                }

                @Override
                public void updatePovertyInfectivity(final float povertyInfectivity) {
                    this.povertyInfectivity = getParameterUpdate(this.povertyInfectivity + povertyInfectivity, "povertyInfectivity");
                }

                @Override
                public String toString() {
                    return "Disease[Name: " + this.getName() + ", Type: " + this.getType() + ", Infectivity: "
                            + this.getInfectivity() + ", Lethality: " + this.getLethality()
                            + ", diseaseAirInfectivity: " + this.getAirInfectivity() + " , diseaseSeaInfectivity: "
                            + this.getSeaInfectivity() + ", diseaseLandInfectivity: " + this.getLandInfectivity()
                            + ", diseaseHeatInfectivity: " + this.getHeatInfectivity() + ", diseaseColdInfectivity: "
                            + this.getColdInfectivity() + ", diseaseCureResistance: " + this.getCureResistance()
                            + ", diseaseHumidityInfectivity: " + this.getHumidityInfectivity()
                            + ", diseaseAridityInfectivity: " + this.getAridityInfectivity()
                            + ", diseasePovertyInfectivity: " + this.getPovertyInfectivity() + "]";
                }

                @Override
                public void initializeObserver(final String name, final PropertyChangeListener listener) {
                    switch (name) {
                        case "infodata":
                            infodataSupport.addPropertyChangeListener(listener);
                            break;
                        default:
                            logger.warn("ProeprtyChangeSupport for the name {} not found.", name);
                            break;
                    }
                }

                @Override
                public void killPeopleRegions(final List<Region> regionList) {

                    regionList.stream()
                            .filter(region -> region.getNumInfected() > 0)
                            .forEach(region -> {
                                region.incDeathPeople(this.calculateNewDeaths(region.getNumInfected()));
                                region.incOrDecInfectedPeople(-this.calculateNewDeaths(region.getNumInfected()));
                            });
                }

                @Override
                public void infectRegions(final List<Region> regionList) {
                    regionList.stream()
                            .filter(region -> region.getNumInfected() > 0)
                            .forEach(region -> region.incOrDecInfectedPeople(this.calculateNewInfected(region.getPopTot(),
                                    region.getNumInfected(), region.getUrban(), region.getPoor(),
                                    region.getClimate().getArid(), region.getClimate().getCold(),
                                    region.getClimate().getHot(), region.getClimate().getHumid())));
                }

                /**
                 * Calculate the new infected.
                 * 
                 * @param population
                 * @param currentInfected
                 * @param urban
                 * @param poor
                 * @param arid
                 * @param cold
                 * @param hot
                 * @param humid
                 * @return
                 * the number of infected to add.
                 */
                private int calculateNewInfected(final int population, final int currentInfected, final float urban,
                        final float poor, final float arid, final float cold, final float hot, final float humid) {
                    if (this.checkIfPositive(population, "population")
                            && this.checkIfPositive(currentInfected, "currentInfected")
                            && this.checkIfPositive(urban, "urban") && this.checkIfPositive(poor, "poor")) {
                        return (int) Math.round(population * ((float) currentInfected / population)
                                * this.calculateInfectivity(urban, hot, cold, humid, arid, poor));
                    }
                    logger.error("The number of population, currentInfected, urban, poor must be at least");
                    return 0;
                }

                /**
                 * Calculate total infectivity.
                 * @param urban
                 * @param poor
                 * @param hot
                 * @param cold
                 * @param humidity
                 * @param aridity
                 * @return
                 */
                private float calculateInfectivity(final float urban, final float poor, final float hot,
                        final float cold, final float humidity, final float aridity) {
                    return this.infectivity * urban + this.heatInfectivity * hot + this.coldInfectivity * cold
                            + this.humidityInfectivity * humidity + this.aridityInfectivity * aridity
                            + this.povertyInfectivity * poor;
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
                    return 0;
                }

                /**
                 * Check positive values.
                 * 
                 * @param number
                 * @param name
                 * @return
                 *         true if number is positive, false otherwise
                 */
                private boolean checkIfPositive(final float number, final String name) {
                    if (number < 0) {
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
                private float getParameterUpdate(final float value, final String name) {
                    final float max_value = 0.16f;
                    if (value < 0 || value > max_value) {
                        logger.error("Error parameter update: The new value of {} is less than 0 or exceeds 1", name);
                        return max_value;
                    }
                    return value;
                }
            };
    }
}
