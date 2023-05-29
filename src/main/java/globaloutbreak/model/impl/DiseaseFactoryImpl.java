package globaloutbreak.model.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import globaloutbreak.model.api.Disease;
import globaloutbreak.model.api.DiseaseFactory;
import globaloutbreak.model.api.Region;

/**
 * Disease factory class.
 */
public class DiseaseFactoryImpl implements DiseaseFactory {

    private float infectivity;
    private float lethality;
    private float airTransmission;
    private float landTransmission;
    private float seaTransmission;
    private float heatTransmission;
    private float coldTransmission;
    private float cureResistance;
    private float humidityResistance;
    private float aridityResistance;
    private float povertyTransmission;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * factory class constructor.
     * 
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
     */
    public DiseaseFactoryImpl(final float infectivity, final float lethality, final float airTransmission,
            final float seaTransmission, final float landTransmission,
            final float heatTransmission, final float coldTransmission, final float cureResistance,
            final float humidityResistance, final float aridityResistance, final float povertyTransmission) {
        this.infectivity = infectivity;
        this.lethality = lethality;
        this.airTransmission = airTransmission;
        this.seaTransmission = seaTransmission;
        this.landTransmission = landTransmission;
        this.heatTransmission = heatTransmission;
        this.coldTransmission = coldTransmission;
        this.cureResistance = cureResistance;
        this.humidityResistance = humidityResistance;
        this.aridityResistance = aridityResistance;
        this.povertyTransmission = povertyTransmission;
    }

    /**
     * Method to create new Disease.
     * 
     * @return
     *         disease
     */
    @Override
    public Disease createDisease(final String diseaseName) {
        return new Disease() {

            private String name = diseaseName;

            /**
             * @return
             *         disease name.
             */
            @Override
            public String getname() {
                return this.name;
            }

            /**
             * @return
             *         infectivity.
             */
            @Override
            public float getInfectivity() {
                return infectivity;
            }

            /**
             * @return
             *         lethality.
             */
            @Override
            public float getLethality() {
                return lethality;
            }

            /**
             * @return
             *         airTransmission.
             */
            @Override
            public float getAirTransmission() {
                return airTransmission;
            }

            /**
             * @return
             *         seaTransmission.
             */
            @Override
            public float getSeaTransmission() {
                return seaTransmission;
            }

            /**
             * @return
             *         landTransmission.
             */
            @Override
            public float getLandTransmission() {
                return landTransmission;
            }

            /**
             * @return
             *         coldTransmission.
             */
            @Override
            public float getColdTransmission() {
                return coldTransmission;
            }

            /**
             * @return
             *         heatTransmission.
             */
            @Override
            public float getHeatTransmission() {
                return heatTransmission;
            }

            /**
             * @return
             *         cureResistance.
             */
            @Override
            public float getCureResistance() {
                return cureResistance;
            }

            /**
             * @return
             *         aridityResistance.
             */
            @Override
            public float getAridityResistance() {
                return aridityResistance;
            }

            /**
             * @return
             *         humidityResistance.
             */
            @Override
            public float getHumidityResistance() {
                return humidityResistance;
            }

            /**
             * @return
             *         povertyTransmission.
             */
            @Override
            public float getPovertyTransmission() {
                return povertyTransmission;
            }

            /**
             * increase or decrease infectivity.
             */
            @Override
            public void setInfectivity(final float increaseinfectivity) {
                infectivity += increaseinfectivity;
            }

            /**
             * increase or decrease lethality.
             */
            @Override
            public void setLethality(final float increaseLethality) {
                lethality += increaseLethality;
            }

            /**
             * increase or decrease airTransmission.
             */
            @Override
            public void setAirTransmission(final float increaseAirTransmission) {
                airTransmission += increaseAirTransmission;
            }

            /**
             * increase or decrease sea transmission.
             */
            @Override
            public void setSeaTransmission(final float increaseSeaTransmission) {
                seaTransmission += increaseSeaTransmission;
            }

            /**
             * increase or decrease land transmission.
             */
            @Override
            public void setLandTransmission(final float increaselandTransmission) {
                landTransmission += increaselandTransmission;
            }

            /**
             * increase or decrease heat transmission.
             */
            @Override
            public void setHeatTransmission(final float increaseHeatTransmission) {
                heatTransmission += increaseHeatTransmission;
            }

            /**
             * increase or decrease cold transmission.
             */
            @Override
            public void setColdTransmission(final float increaseColdTransmission) {
                coldTransmission += increaseColdTransmission;
            }

            /**
             * increase or decrease cure resistance.
             */
            @Override
            public void setCureResistance(final float increaseCureResistance) {
                cureResistance += increaseCureResistance;
            }

            /**
             * increase or decrease aridity resistance.
             */
            @Override
            public void setAridityResistance(final float increaseAridityResistance) {
                aridityResistance += increaseAridityResistance;
            }

            /**
             * increase or decrease humidity resistance.
             */
            @Override
            public void setHumidityResistance(final float increaseHumidityResistance) {
                humidityResistance += increaseHumidityResistance;
            }

            /**
             * increase or decrease poverty transmission.
             */
            @Override
            public void setPovertyTransmission(final float increasePovertyTransmission) {
                povertyTransmission += increasePovertyTransmission;
            }

            /**
             * kill people regions.
             * 
             * @param regionList List of all regions
             */
            @Override
            public void killPeopleRegions(final List<Region> regionList) {
                /*
                 * regionList.stream()
                 * .filter(region -> region.getNumInfected() > 0)
                 * .forEach(region ->
                 * region.incDeathPeople(this.calculateNewDeaths(region.getNumInfected())));
                 */
            }

            /**
             * infect people regions.
             * 
             * @param regionList List of all regions
             */
            @Override
            public void infectRegions(final List<Region> regionList) {
                /*
                 * regionList.stream()
                 * .filter(region -> region.getNumInfected() > 0)
                 * .forEach(region ->
                 * region.IncOrDecNuminfected(calculateNewInfected(region.getPopTot(),
                 * region.getNumInfected(), region.getUrban(), region.getPoor())));
                 */
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
                if (population >= 0 && currentInfected >= 0 && urban >= 0 && poor >= 0) {
                    float stateInfectivity = (infectivity + landTransmission * urban + povertyTransmission * poor)
                            / 100;

                    return (int) Math.round(population * currentInfected * stateInfectivity);
                }
                logger.error("The number of population, currentInfected, urban, poor must be at least");
                return 0;
            }

            /**
             * calculate number of new deaths.
             * 
             * @param infected
             */
            private int calculateNewDeaths(int infected) {
                if (infected >= 0) {
                    return (int) Math.round(infected * this.getLethality());
                }
                logger.error("The number of infected must be at least");
                return -1;
            }
        };
    }
}
