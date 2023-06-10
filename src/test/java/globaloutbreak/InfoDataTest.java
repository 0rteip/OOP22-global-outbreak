package globaloutbreak;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.controller.Controller;
import globaloutbreak.controller.ControllerImpl;
import globaloutbreak.diseasereader.DiseaseReader;
import globaloutbreak.diseasereader.DiseaseReaderImpl;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.model.infodata.InfoDataImpl;
import globaloutbreak.view.View;
import globaloutbreak.view.ViewImpl;

class InfoDataTest {

    private static final int FIRST_NUM = 200_000;
    private static final int SECOND_NUM = 300_000;
    private static final int THIRD_NUM = 400_000;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void testIncreaseDnaPoints() {
        logger.info("Starting testDnaPoints()");

        DiseaseReader reader = new DiseaseReaderImpl();
        
        final Controller controller = new ControllerImpl(null);
        controller.readDiseasesNames();
        controller.choosenDisease(reader.getDiseases().get(0).getType());
       // Disease disease = controller.getDisease();

        InfoData infodata = new InfoDataImpl(1_500_000);

        Assertions.assertEquals(1, infodata.getPoints());
        logger.info("Dna Points are {} and expected was 1", infodata.getPoints());

        
        //disease.infectRegions();

        Assertions.assertTrue(infodata.getPoints() > 1);
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
