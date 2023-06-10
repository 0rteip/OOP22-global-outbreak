package globaloutbreak;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.model.infodata.InfoDataImpl;

class InfoDataTest {

    private static final int FIRST_NUM = 200_000;
    private static final int SECOND_NUM = 300_000;
    private static final int THIRD_NUM = 400_000;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static class Disease {

        private int numInfected;
        private int deaths;
        private Map<String, PropertyChangeSupport> observers = new HashMap<>();

        Disease(final int numInfected, final int deaths) {
            this.numInfected = numInfected;
            this.deaths = deaths;
            observers.put("DnaPoints", new PropertyChangeSupport(this));
        }

        public void infect(final int infected) {
            int oldInfected = this.numInfected;
            this.numInfected += infected;
            observers.get("DnaPoints").firePropertyChange("infected", oldInfected, this.numInfected);
        }

        public void kill(final int calculateNewDeaths) {
            int oldDeaths = this.deaths;
            this.deaths += calculateNewDeaths;
            observers.get("DnaPoints").firePropertyChange("deaths", oldDeaths, this.deaths);
        }

        public void addPropertyChangeListener(final String eventName, final PropertyChangeListener listener) {
            observers.get(eventName).addPropertyChangeListener(listener);
        }

        public int getDeaths() {
            return this.deaths;
        }

        public int getInfected() {
            return this.numInfected;
        }

    }

    @Test
    void testIncreaseDnaPoints() {
        logger.info("Starting testDnaPoints()");

        Disease disease = new Disease(THIRD_NUM, THIRD_NUM);

        InfoData infodata = new InfoDataImpl(500_000);

        Assertions.assertEquals(1, infodata.getPoints());
        logger.info("Dna Points are {} and expected was 1", infodata.getPoints());

        disease.infect(FIRST_NUM);

        Assertions.assertTrue(infodata.getPoints() > 1);
        logger.info("Infected: {} - Dna Points are {} and expected were more than 1", disease.getInfected(),
                infodata.getPoints());

        disease.kill(FIRST_NUM);

        int points = infodata.getPoints();
        Assertions.assertTrue(infodata.getPoints() > 2);
        logger.info("Deaths: {} - Dna Points are {} and expected were more than 2", disease.getDeaths(),
                infodata.getPoints());

        disease.infect(SECOND_NUM);

        Assertions.assertEquals(points, infodata.getPoints());
        logger.info("Infected: {} - Dna Points are {} and expected were {}", disease.getInfected(),
                infodata.getPoints(), points);

        disease.kill(FIRST_NUM);

        Assertions.assertEquals(points, infodata.getPoints());
        logger.info("Deaths: {} - Dna Points are {} and expected were {}", disease.getDeaths(), infodata.getPoints(),
                points);

        disease.infect(THIRD_NUM);

        Assertions.assertTrue(infodata.getPoints() > points);
        logger.info("Dna Points are {} and expected were more than {}", infodata.getPoints(), points);

        disease.kill(THIRD_NUM);

        Assertions.assertTrue(infodata.getPoints() > points + 1);
        logger.info("Dna Points are {} and expected were more than {}", infodata.getPoints(), points + 1);

        logger.info("testDnaPoints() gone well");
    }
}
