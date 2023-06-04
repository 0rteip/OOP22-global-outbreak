package globaloutbreak;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import globaloutbreak.model.api.Region;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.disease.DiseaseFactory;
import globaloutbreak.model.disease.DiseaseFactoryImpl;

class InfectionDeathTest {

    private static final int EXPECTED_INFECTS1 = 60_500;
    private static final int EXPECTED_INFECTS2 = 0;
    private static final int EXPECTED_DEATHS1 = 6_050;
    private static final int EXPECTED_DEATHS2 = 0;
    private static final int EXPECTED_INFECTS3 = 54_450;
    private static final int EXPECTED_INFECTS4 = 0;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static class RegionImpl implements Region {

        private Integer numInfected;
        private final Integer totPop;
        private Integer deaths;
        private final Integer urban;
        private final Integer poor;

        RegionImpl(final int numInfected, final int totPop, final int deaths, final int urban, final int poor) {
            this.numInfected = numInfected;
            this.totPop = totPop;
            this.deaths = deaths;
            this.urban = urban;
            this.poor = poor;
        }

        @Override
        public int getNumInfected() {
            return this.numInfected;
        }

        @Override
        public void incDeathPeople(final int calculateNewDeaths) {
            this.deaths += calculateNewDeaths;
        }

        @Override
        public int getPopTot() {
            return this.totPop;
        }

        @Override
        public Integer getUrban() {
            return this.urban;
        }

        @Override
        public void incOrDecNuminfected(final int calculateNewInfected) {
            this.numInfected += calculateNewInfected;
        }

        @Override
        public Integer getPoor() {
            return this.poor;
        }

        @Override
        public Integer getDeaths() {
            return this.deaths;
        }

    }

    /**
     * Test if the methods infectRegions and killPeopleRegions from Disease class
     * works as expected.
     */
    @Test
    void testKillPeople() {
        final DiseaseFactory diseaseFactory = new DiseaseFactoryImpl();
        final Disease disease = diseaseFactory.createDisease("Influenza", "Virus", 0.5f, 0.1f, 0.3f, 0.2f,
                0.1f, 0.2f, 0.1f, 0.2f, 0.3f, 0.1f, 0.2f);

        final List<Region> regionList = new ArrayList<>();
        final Region region1 = new RegionImpl(55_000, 1_000_000, 0, 85, 5);
        final Region region2 = new RegionImpl(0, 500_000, 0, 30, 90);

        regionList.add(region1);
        regionList.add(region2);

        disease.infectRegions(regionList);

        Assertions.assertEquals(EXPECTED_INFECTS1, region1.getNumInfected());
        Assertions.assertEquals(EXPECTED_INFECTS2, region2.getNumInfected());
        disease.killPeopleRegions(regionList);

        Assertions.assertEquals(EXPECTED_DEATHS1, region1.getDeaths());
        Assertions.assertEquals(EXPECTED_DEATHS2, region2.getDeaths());
        Assertions.assertEquals(EXPECTED_INFECTS3, region1.getNumInfected());
        Assertions.assertEquals(EXPECTED_INFECTS4, region2.getNumInfected());
        logger.info("KillTest gone well");
    }
}
