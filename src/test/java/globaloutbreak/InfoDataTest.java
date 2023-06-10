package globaloutbreak;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.controller.Controller;
import globaloutbreak.controller.ControllerImpl;
import globaloutbreak.diseasereader.DiseaseReader;
import globaloutbreak.diseasereader.DiseaseReaderImpl;
import globaloutbreak.model.Model;
import globaloutbreak.model.ModelImpl;
import globaloutbreak.model.cure.RegionCureStatus;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.model.infodata.InfoDataImpl;
import globaloutbreak.model.observer.InfoDataRegionObserver;
import globaloutbreak.model.region.Climate;
import globaloutbreak.model.region.ClimateImpl;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.region.TransmissionMean;

class InfoDataTest {

    private static final int POPULATION = 2_000_000;
    private static final int FIRST_NUM = 200_000;
    private static final int SECOND_NUM = 300_000;
    private static final int THIRD_NUM = 400_000;
    private final float PARAMETER = 0.1f;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void testIncreaseDnaPoints() {
        final Region reg = new Region() {

                private final int population = POPULATION;
                private int death = 0;
                private int infected = 0;
                private final float urban = PARAMETER;
                private final float poor = PARAMETER;
                Climate climate = new ClimateImpl(PARAMETER, PARAMETER);
                private final float hot = PARAMETER;
                private final float cold = PARAMETER;
                private final float arid = PARAMETER;
                private final float humid = PARAMETER;
                PropertyChangeSupport infodataSupport = new PropertyChangeSupport(this);

                @Override
                public int getFacilities() {
                    throw new UnsupportedOperationException("Unimplemented method 'incDeathPeople'");
                }

                @Override
                public long getPopTot() {
                    return population;
                }

                @Override
                public long getNumDeath() {
                    return death;
                }

                @Override
                public void setCureStatus(final RegionCureStatus status) {
                    throw new UnsupportedOperationException("Unimplemented method 'incDeathPeople'");
                }

                @Override
                public RegionCureStatus getCureStatus() {
                    throw new UnsupportedOperationException("Unimplemented method 'incDeathPeople'");
                }

                @Override
                public void incDeathPeople(final long dead) {
                    this.death += dead;
                }

                @Override
                public void incOrDecInfectedPeople(final long infected) {
                    this.infected += infected;
                }

                @Override
                public List<TransmissionMean> getTrasmissionMeans() {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'getTrasmissionMeans'");
                }

                @Override
                public void initializeObserver(PropertyChangeListener listener) {
                    this.infodataSupport.addPropertyChangeListener(listener);
                }

                @Override
                public float calcPercInfected() {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'calcPercInfected'");
                }

                @Override
                public long getNumInfected() {
                    return this.getNumInfected();
                }

                @Override
                public String getName() {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'getName'");
                }

                @Override
                public float getUrban() {
                    return this.urban;
                }

                @Override
                public float getPoor() {
                    return this.poor;
                }

                @Override
                public int getColor() {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'getColor'");
                }

                @Override
                public Climate getClimate() {
                   return this.climate;
                }

            };
            
        logger.info("Starting testDnaPoints()");

        DiseaseReader reader = new DiseaseReaderImpl();
        
        final Controller controller = new ControllerImpl(null);
        final Model model = new ModelImpl();
        controller.readDiseasesNames();
        controller.choosenDisease(reader.getDiseases().get(0).getType());
       Disease disease = controller.getDisease();

        InfoData infodata = new InfoDataImpl(1_500_000);
        PropertyChangeListener infoDataObserver = new InfoDataRegionObserver(infodata);
        reg.initializeObserver(infoDataObserver);

        Assertions.assertEquals(1, infodata.getPoints());

        logger.info("Dna Points are {} and expected was 1", infodata.getPoints());
        final List<Region> regionList = new ArrayList<>();
        reg.incOrDecInfectedPeople(FIRST_NUM);
        Assertions.assertEquals(FIRST_NUM, infodata.getPoints());

        disease.infectRegions(regionList);

        /*logger.info("Infected: {} - Dna Points are {} and expected were more than 1", disease.getInfected(),
                infodata.getPoints());

        //disease.kill(FIRST_NUM);

        int points = infodata.getPoints();
        Assertions.assertTrue(infodata.getPoints() > 2);
        logger.info("Deaths: {} - Dna Points are {} and expected were more than 2", disease.getDeaths(),
                infodata.getPoints());

        //disease.infect(SECOND_NUM);

        Assertions.assertEquals(points, infodata.getPoints());
        logger.info("Infected: {} - Dna Points are {} and expected were {}", disease.getInfected(),
                infodata.getPoints(), points);

        //disease.kill(FIRST_NUM);

        Assertions.assertEquals(points, infodata.getPoints());
        logger.info("Deaths: {} - Dna Points are {} and expected were {}", disease.getDeaths(), infodata.getPoints(),
                points);

        //disease.infect(THIRD_NUM);

        Assertions.assertTrue(infodata.getPoints() > points);
        logger.info("Dna Points are {} and expected were more than {}", infodata.getPoints(), points);

        //disease.kill(THIRD_NUM);

        Assertions.assertTrue(infodata.getPoints() > points + 1);
        logger.info("Dna Points are {} and expected were more than {}", infodata.getPoints(), points + 1);

        logger.info("testDnaPoints() gone well"); */ 
    }
}
