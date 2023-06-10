package globaloutbreak.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import globaloutbreak.controller.disease.DiseaseController;
import globaloutbreak.controller.disease.DiseaseControllerImpl;
import globaloutbreak.controller.newsobserver.NewsObserver;
import globaloutbreak.controller.region.RegionController;
import globaloutbreak.controller.region.RegionControllerImpl;
import globaloutbreak.diseasereader.DiseaseReader;
import globaloutbreak.diseasereader.DiseaseReaderImpl;
import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.Model;
import globaloutbreak.model.ModelImpl;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.cure.Cure;
import globaloutbreak.model.cure.SimpleCureReaderImpl;
import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.model.voyage.Voyage;
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
    private final View view;
    private final Model model = new ModelImpl();
    private final GameLoop gameLoop = new GameLoop();
    private final GameSettings settings = new GameSettingsImpl();
    private final RegionController regionController = new RegionControllerImpl();
    private final DiseaseController diseaseController = new DiseaseControllerImpl();

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
        this.view = view;
        this.model.addNewsListener(new NewsObserver(this));
        this.model.setRegions(regionController.getRegions());
    }

    @Override
    public void selectedRegion(final int color) {
        this.model.selectedRegion(this.regionController.findRegionByColor(color));
    }

    @Override
    public void selectedMutation(final Mutation mutation) {
        throw new UnsupportedOperationException("Unimplemented method 'selectedMutation'");
    }

    @Override
    public void updateInfo() {
        this.model.updateInfoData();
    }

    @Override
    public InfoData displayInfo() {
        return this.model.getInfo();
    }

    @Override
    public void displayMessage(final Message message) {
        this.view.displayMessage(message);
    }

    @Override
    public void startVoyage(final Voyage voyage) {
        this.view.displayVoyage(voyage);
    }

    // @formatter:off
    // @SuppressFBWarnings(
    //     value = "EI_EXPOSE_REP",
    //     justification = "settings is casted to a only getters interface and considerable immutable"
    // )
    // @formatter:on
    // It could be possible to suppress the warning because GameSettings is an only
    // getter interface, but i think this way is safer
    @Override
    public GameSettingsGetter getSettings() {
        return this.settings.clone();
    }

    @Override
    public void choosenDisease(final String type) {
        this.model.setDisease(this.diseaseController.createDisease(type));
        final Cure cure = new SimpleCureReaderImpl().getSimpleCure(this.model.getRegions());
        if (cure.isConsistent()) {
            this.model.setCure(cure);
        } else {
            this.logger.warn("Unable to create a Cure instance, something went wrong");
        }
    }

    @Override
    public void choosenDiseaseName(final String name) {
        this.model.setDiseaseName(name);
    }

    @Override
    public void readDiseasesNames() {
        final DiseaseReader reader = new DiseaseReaderImpl();
        this.view.setDiseasesData(reader.getDiseases());
        this.diseaseController.readFile(reader.getDiseases());
    }

    @Override
    public Map<TypeOfInfo, String> getInfoSingleRegion() {
        final Map<TypeOfInfo, String> info = new HashMap<>();
        final Optional<Region> r = this.model.getSelectedRegion();
        if (r.isPresent()) {
            info.put(TypeOfInfo.INFETTI, Integer.toString(r.get().getNumInfected()));
            info.put(TypeOfInfo.MORTI, Integer.toString(r.get().getNumDeath()));
            info.put(TypeOfInfo.REGION, r.get().getName());
        } /*
           * else {
           * info.put(TypeOfInfo.INFETTI, Integer.toString(model.getInfo()));
           * info.put(TypeOfInfo.MORTI, Integer.toString(r.get().getNumDeath()));
           * info.put(TypeOfInfo.REGION, r.get().getName());
           * }
           */
        return info;
    }

    @Override
    public void setGameSpeed(final GameSpeed gameSpeed) {
        logger.info("Setted game speed to: {}", gameSpeed.toString());
        this.settings.setGameSpeed(gameSpeed);
    }

    @Override
    public void startStop() {
        if (this.model.isDiseaseSet()) {
            if (!this.gameLoop.isAlive()) {
                this.gameLoop.start();
            } else {
                logger.info(this.gameLoop.isRunning() ? "STOP loop, pause" : "RESTART loop");
                this.gameLoop.startStop();
            }
        }
    }

    @Override
    public boolean isGameRunning() {
        return this.gameLoop.isRunning();
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
