package globaloutbreak.gameloop;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.controller.Controller;
import globaloutbreak.controller.ControllerImpl;

/**
 * Test for GameLoop.
 */
final class GameLoopTest {

    private final Controller controller = new ControllerImpl(null);
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Test if game close because controllerImpl cot create a cure by default.
     */
    @Test
    public void testGameEndIfNoCureSetted() {
        this.logger.info("Starting testGameEndIfNoCureSetted()");
        assertFalse(this.controller.isGameRunning());
        this.controller.startStop();
        assertFalse(this.controller.isGameRunning());
        this.logger.info("testGameEndIfNoCureSetted() gone well");
    }
}
