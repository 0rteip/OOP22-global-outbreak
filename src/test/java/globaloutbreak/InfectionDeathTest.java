package globaloutbreak;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import globaloutbreak.model.cure.RegionCureStatus;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.disease.DiseaseFactory;
import globaloutbreak.model.disease.DiseaseFactoryImpl;
import globaloutbreak.model.region.Climate;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.region.TransmissionMean;

class InfectionDeathTest {

    private static final int INITIAL_INFECTS = 55_000;
    private static final int INITIAL_DEATHS = 5_000;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Region region = new Region() {

        static final int POPULATION = 1_000_000;
        static final float POOR = 0.2f;
        static final float URBAN = 0.4f;
        private int infected = INITIAL_INFECTS;
        private int deaths = INITIAL_DEATHS;

        @Override
        public long getNumInfected() {
            return this.infected;
        }

        @Override
        public void incDeathPeople(final long calculateNewDeaths) {
            this.deaths += calculateNewDeaths;
        }

        @Override
        public long getPopTot() {
            return POPULATION;
        }

        @Override
        public float getUrban() {
            return URBAN;
        }

        @Override
        public void incOrDecInfectedPeople(final long calculateNewInfected) {
            this.infected += calculateNewInfected;
        }

        @Override
        public float getPoor() {
            return POOR;
        }

        @Override
        public long getNumDeath() {
            return this.deaths;
        }

        @Override
        public RegionCureStatus getCureStatus() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getCureStatus'");
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
        public Climate getClimate() {
            return new Climate() {
                static final float HOT = 0.1f;
                static final float COLD = 0.1f;
                static final float ARID = 0.9f;
                static final float HUMID = 0.9f;

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

        @Override
        public float calcPercInfected() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'calcPercInfected'");
        }

        @Override
        public String getName() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getName'");
        }

        @Override
        public int getColor() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getColor'");
        }

        @Override
        public List<TransmissionMean> getTrasmissionMeans() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getTrasmissionMeans'");
        }

        @Override
        public void initializeObserver(final PropertyChangeListener listener) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'initializeObserver'");
        }
    };

    /**
     * Test if the methods infectRegions and killPeopleRegions from Disease class
     * works as expected.
     */
    @Test
    void testKillPeople() {
        final DiseaseFactory diseaseFactory = new DiseaseFactoryImpl();
        final Disease disease = diseaseFactory.createDisease("Virus", 0.1f, 0.1f, 0.1f, 0.1f,
                0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f);
        final List<Region> regionList = new ArrayList<>();

        regionList.add(region);
        final float expectedInfectivity = (float) (disease.getInfectivity() * region.getUrban()
                + disease.getAridityInfectivity() * region.getClimate().getArid()
                + disease.getHumidityInfectivity() * region.getClimate().getHumid()
                + disease.getColdInfectivity() * region.getClimate().getCold()
                + disease.getHeatInfectivity() * region.getClimate().getHot()
                + disease.getPovertyInfectivity() * region.getPoor());
        final long expectedInfected = (long) (region.getNumInfected() * expectedInfectivity) + region.getNumInfected();
        disease.infectRegions(regionList);
        Assertions.assertEquals(expectedInfected, region.getNumInfected());
        final long expectedDeaths = (long) (region.getNumInfected() * disease.getLethality()) + region.getNumDeath();
        disease.killPeopleRegions(regionList);

        Assertions.assertEquals(expectedDeaths, region.getNumDeath());
        logger.info("KillTest gone well");
    }
}
