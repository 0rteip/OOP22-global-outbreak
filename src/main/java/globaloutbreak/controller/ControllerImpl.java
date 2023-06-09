package globaloutbreak.controller;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import globaloutbreak.controller.disease.DiseaseController;
import globaloutbreak.controller.disease.DiseaseControllerImpl;
import globaloutbreak.controller.newsobserver.NewsObserver;
import globaloutbreak.diseasereader.DiseaseReader;
import globaloutbreak.diseasereader.DiseaseReaderImpl;
import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.Model;
import globaloutbreak.model.ModelImpl;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.voyage.Voyage;
import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.settings.gamesettings.GameSettings;
import globaloutbreak.settings.gamesettings.GameSettingsGetter;
import globaloutbreak.settings.gamesettings.GameSettingsImpl;
import globaloutbreak.view.View;
import javafx.application.Platform;

/**
 * Controller implementation.
 */
public final class ControllerImpl implements Controller {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final GameSettings settings = new GameSettingsImpl();
    private final GameLoop gameLoop = new GameLoop();
    private final DiseaseController diseaseController;
    private final Model model;
    private final View view;

    /**
     * Create a controller.
     * 
     * @param view
     *             View
     */
    // @formatter:off
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "We need to use the correct instance of View"
    )
    // @formatter:on
    public ControllerImpl(final View view) {
        // System.out.println("Velocità: " + settings.getGameSpeed());
        this.model = new ModelImpl();
        this.model.addNesListener(new NewsObserver(this));
        this.diseaseController = new DiseaseControllerImpl();
        this.view = view;
    }

    @Override
    public void selectedRegion(final Region region) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectedRegion'");
    }

    @Override
    public void selectedMutation(final Mutation mutation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectedMutation'");
    }

    @Override
    public void updateInfo() {
        this.model.updateInfoData();
    }

    @Override
    public void displayInfo(){
        this.view.displayInfo(this.model.getInfo());
    }

    @Override
    public void displayMessage(final Message message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayMessage'");
    }

    @Override
    public void startVoyage(final Voyage voyage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startVoyage'");
    }

    @Override
    public void setGameSpeed(final GameSpeed gameSpeed) {
        logger.info("Setted game speed to: {}", gameSpeed.toString());
        this.settings.setGameSpeed(gameSpeed);
    }

    @Override
    public void startStop() {
        if (!this.gameLoop.isAlive()) {
            this.gameLoop.start();
        } else {
            logger.info(this.gameLoop.isRunning() ? "STOP loop, pause" : "RESTART loop");
            this.gameLoop.startStop();
        }
    }

    @Override
    public boolean isGameRunning() {
        return this.gameLoop.isRunning();
    }

    // // @formatter:off
    // @SuppressFBWarnings(
    // value = "EI_EXPOSE_REP",
    // justification = "settings is casted to a only getters interface and
    // considerable immutable"
    // )
    // // @formatter:on
    // It could be possible to suppress the warning because GameSettings is an only
    // getter interface, but i think this way is safer
    @Override
    public GameSettingsGetter getSettings() {
        return this.settings.clone();
    }

    @Override
    public void choosenDisease(final String type) {
        this.model.setDisease(this.diseaseController.createDisease(type));
    }

    @Override
    public void choosenDiseaseName(final String name) {
        this.model.setDiseaseName(name);
    }

    @Override
    public void createDisease(final String type) {
    }

    @Override
    public void readDiseasesNames() {
        final DiseaseReader reader = new DiseaseReaderImpl();
        this.view.setDiseasesData(reader.getDiseases());
        this.diseaseController.readFile(reader.getDiseases());
    }

    @Override
    public void quit() {
        Platform.exit();
    }

    private final class GameLoop extends Thread {

        private volatile boolean isRunning;
        private long startTime;
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();

        GameLoop() {
            this.setDaemon(true);
        }

        @Override
        public void run() {
            this.lock.lock();
            try {
                this.isRunning = true;
            } finally {
                this.lock.unlock();
            }
            logger.info("Start GameLoop");
            while (this.isRunning() && !model.isGameOver()) {
                this.startTime = System.currentTimeMillis();
                this.update();
                this.render();
                this.remainingTime();

                this.lock.lock();
                try {
                    if (!this.isRunning) {
                        try {
                            this.condition.await();
                        } catch (InterruptedException e) {
                            logger.warn("Loop problem on await function: ", e);
                        }
                    }
                } finally {
                    this.lock.unlock();
                }
            }
            logger.info("Quitting GameLoop");
            quit();
        }

        private void update() {
            // System.out.println("update");
            // ControllerImpl.this.update(dayCicle);
            // if (ControllerImpl.this.model.isSimulationOver()) {
            // ControllerImpl.this.view.setSimulationOver();
            // }
        }

        private void render() {
            // System.out.println("render");
            // System.out.println(LocalTime.now());

            // ControllerImpl.this.view.render(ControllerImpl.this.model.getFoods(),
            // ControllerImpl.this.model.getOrganisms());
        }

        private void remainingTime() {
            final long elapsedTime = System.currentTimeMillis() - this.startTime;
            final int timeUntilNextLoop = Math.round(settings.getGameSpeed().getDuration() * 1000 - elapsedTime);
            if (timeUntilNextLoop > 0) {
                try {
                    Thread.sleep(timeUntilNextLoop);
                } catch (InterruptedException e) {
                    logger.warn("Loop problem on sleep function:", e);
                }
            }
        }

        private void startStop() {
            this.lock.lock();
            try {
                this.isRunning = !this.isRunning;
                if (this.isRunning) {
                    this.condition.signal();
                }
            } finally {
                this.lock.unlock();
            }
        }

        private boolean isRunning() {
            this.lock.lock();
            try {
                return this.isRunning;
            } finally {
                this.lock.unlock();
            }
        }
    }
}
