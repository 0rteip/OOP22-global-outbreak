package globaloutbreak;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import globaloutbreak.model.api.ClimateImpl;
import globaloutbreak.model.api.Region;
import globaloutbreak.model.cure.RegionCureStatus;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.disease.DiseaseFactory;
import globaloutbreak.model.disease.DiseaseFactoryImpl;

class InfectionDeathTest {

    private static final int INITIAL_INFECTS = 55_000;
    private static final int INITIAL_DEATHS = 5_000;
    private static final int EXPECTED_INFECTS1 = 80_850;
    private static final int EXPECTED_DEATHS1 = 13_085;
    private static final int EXPECTED_INFECTS3 = 72_765;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Region region = new Region() {

        static final int POPULATION = 1_000_000;
        static final float POOR = 0.2f;
        static final float URBAN = 0.4f;
        private int infected = INITIAL_INFECTS;
        private int deaths = INITIAL_DEATHS;

        @Override
        public int getNumInfected() {
            return this.infected;
        }

        @Override
        public void incDeathPeople(final int calculateNewDeaths) {
            this.deaths += calculateNewDeaths;
        }

        @Override
        public int getPopTot() {
            return POPULATION;
        }

        @Override
        public float getUrban() {
            return URBAN;
        }

        @Override
        public void incOrDecNuminfected(final int calculateNewInfected) {
            this.infected += calculateNewInfected;
        }

        @Override
        public float getPoor() {
            return POOR;
        }

        @Override
        public int getDeath() {
            return this.deaths;
        }

        @Override
        public RegionCureStatus getCureStatus() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getCureStatus'");
        }

        @Override
        public int getTotalPopulation() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getTotalPopulation'");
        }

        @Override
        public void setCureStatus(final RegionCureStatus started) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setCureStatus'");
        }

        @Override
        public int getFacilities() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getFacilities'");
        }

        @Override
        public ClimateImpl getClimateImpl() {
            return new ClimateImpl() {

                static final float HOT = 0.1f;
                static final float COLD = 0.2f;
                static final float ARID = 0.6f;
                static final float HUMID = 0.1f;

                @Override
                public float getHot() {
                    return HOT;
                }

                @Override
                public float getCold() {
                    return COLD;
                }

                @Override
                public float getArid() {
                    return ARID;
                }

                @Override
                public float getHumid() {
                    return HUMID;
                }

            };
        }
    };

    /**
     * Test if the methods infectRegions and killPeopleRegions from Disease class
     * works as expected.
     */
    @Test
    void testKillPeople() {
        final DiseaseFactory diseaseFactory = new DiseaseFactoryImpl();
        final Disease disease = diseaseFactory.createDisease("Virus", 0.5f, 0.1f, 0.3f, 0.2f,
                0.1f, 0.2f, 0.1f, 0.2f, 0.3f, 0.1f, 0.2f);

        final List<Region> regionList = new ArrayList<>();

        regionList.add(region);
        disease.infectRegions(regionList);

        Assertions.assertEquals(EXPECTED_INFECTS1, region.getNumInfected());
        disease.killPeopleRegions(regionList);

        Assertions.assertEquals(EXPECTED_DEATHS1, region.getDeath());
        Assertions.assertEquals(EXPECTED_INFECTS3, region.getNumInfected());
        logger.info("KillTest gone well");
    }
}
