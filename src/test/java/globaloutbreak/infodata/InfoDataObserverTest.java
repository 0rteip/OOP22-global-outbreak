package globaloutbreak.infodata;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import globaloutbreak.model.cure.RegionCureStatus;
import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.model.infodata.InfoDataImpl;
import globaloutbreak.model.observer.InfoDataRegionObserver;
import globaloutbreak.model.region.Climate;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.region.TransmissionMean;

/**
 * Class InfoDataObserverTest.
 */
final class InfoDataObserverTest {
    private static final int EXPECTED_INITIAL_POINTS = 1;
    private static final int EXPECTED_POINTS_SECOND = 2;
    private static final int EXPECTED_POINTS_THIRD = 3;
    private static final long UPDATE_INFECTED = 20_000_000;
    private static final long UPDATE_DEATHS = 15_000_000;

    private final Region reg = new Region() {

        private static final long TOT_POP = 100_000_000;
        private long infected;
        private long deaths;
        private PropertyChangeSupport support = new PropertyChangeSupport(this);

        @Override
        public long getNumInfected() {
            return this.infected;
        }

        @Override
        public void incDeathPeople(final long calculateNewDeaths, final Boolean byEvent) {
            this.deaths += calculateNewDeaths;
        }

        @Override
        public long getPopTot() {
            return TOT_POP;
        }

        @Override
        public float getUrban() {
            return 0f;
        }

        @Override
        public void incOrDecInfectedPeople(final long calculateNewInfected) {
            this.infected += calculateNewInfected;
            support.firePropertyChange("infectedRegion", this.infected - calculateNewInfected, this.infected);
        }

        @Override
        public float getPoor() {
            return 0f;
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
        public int getFacilities() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getFacilities'");
        }

        @Override
        public Climate getClimate() {
            return new Climate() {

                @Override
                public float getArid() {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'getArid'");
                }

                @Override
                public float getHumid() {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'getHumid'");
                }

                @Override
                public float getHot() {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'getHot'");
                }

                @Override
                public float getCold() {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'getCold'");
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
            this.support.addPropertyChangeListener(listener);
        }

        @Override
        public long getDeathByVirus() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getDeathByVirus'");
        }

        @Override
        public void setCureStatus(final RegionCureStatus started) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setCureStatus'");
        }
    };

    @Test
    void infoDataTest() {
        final InfoData infodata = new InfoDataImpl(reg.getPopTot());
        final PropertyChangeListener infodataObserver = new InfoDataRegionObserver(infodata);
        final List<Region> regionList = new ArrayList<>();
        regionList.add(reg);
        reg.initializeObserver(infodataObserver);

        Assertions.assertEquals(EXPECTED_INITIAL_POINTS, infodata.getPoints());

        reg.incOrDecInfectedPeople(UPDATE_INFECTED);
        reg.incDeathPeople(UPDATE_DEATHS, null);

        Assertions.assertTrue(
                infodata.getPoints() >= EXPECTED_POINTS_SECOND && infodata.getPoints() <= EXPECTED_POINTS_THIRD);

        final int expectedPointsFourth = infodata.getPoints() + EXPECTED_POINTS_SECOND;
        final int expectedPointsFifth = infodata.getPoints() + EXPECTED_INITIAL_POINTS + EXPECTED_POINTS_THIRD;
        infodata.updateTotalDeathsAndInfected(regionList);

        Assertions.assertTrue(
                expectedPointsFourth <= infodata.getPoints() && expectedPointsFifth >= infodata.getPoints());
    }
}
