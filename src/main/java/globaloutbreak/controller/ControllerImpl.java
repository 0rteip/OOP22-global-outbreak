package globaloutbreak.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import globaloutbreak.model.endcauses.EndCauses;
import globaloutbreak.controller.disease.DiseaseController;
import globaloutbreak.controller.disease.DiseaseControllerImpl;
import globaloutbreak.controller.event.EventController;
import globaloutbreak.controller.event.EventControllerImpl;
import globaloutbreak.controller.mutation.MutationController;
import globaloutbreak.controller.mutation.MutationControllerImpl;
import globaloutbreak.controller.observer.CatastropheObserver;
import globaloutbreak.controller.observer.CureObserver;
import globaloutbreak.controller.observer.NewsObserver;
import globaloutbreak.controller.region.RegionController;
import globaloutbreak.controller.region.RegionControllerImpl;
import globaloutbreak.controller.region.TypeOfInfo;
import globaloutbreak.controller.voyage.VoyageController;
import globaloutbreak.controller.voyage.VoyageControllerImpl;
import globaloutbreak.diseasereader.DiseaseReader;
import globaloutbreak.diseasereader.DiseaseReaderImpl;
import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.Model;
import globaloutbreak.model.ModelImpl;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.message.MessageType;
import globaloutbreak.model.mutation.Mutation;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.cure.Cure;
import globaloutbreak.model.cure.SimpleCureReaderImpl;
import globaloutbreak.model.disease.Disease;
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

    private final Model model = new ModelImpl();
    private final GameSettings settings = new GameSettingsImpl();
    private final GameLoop gameLoop = new GameLoop();
    private final DiseaseController diseaseController = new DiseaseControllerImpl();
    private final View view;
    private final RegionController regionController = new RegionControllerImpl();
    private final MutationController mutationController;
    private final VoyageController voyageController = new VoyageControllerImpl();
    private final EventController eventController = new EventControllerImpl();

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
        this.mutationController = new MutationControllerImpl();
        this.view = view;
        this.model.addListener(new NewsObserver(this));
        this.model.addListener(new CatastropheObserver(this));
        this.model.addListener(new CureObserver(this));
        this.model.setRegions(regionController.getRegions());
        this.model.setVoyages(voyageController.createVoyage());
        this.model.setEvents(eventController.createEvents());
    }

    @Override
    public void selectedRegion(final Optional<Integer> color) {
        if (color.isPresent()) {
            this.model.selectedRegion(Optional.of(this.regionController.findRegionByColor(color.get())));
        } else {
            this.model.selectedRegion(Optional.empty());
        }

    }

    @Override
    public void selectedMutation(final Mutation mutation) {
        throw new UnsupportedOperationException("Unimplemented method 'selectedMutation'");
    }

    @Override
    public void updateInfo() {
    }

    @Override
    public InfoData displayInfo() {
        return this.model.getInfo();
    }

    @Override
    public void displayMessage(final Message message) {
        this.view.displayMessage(message);
    }

    private void createAndDisplayMessage(final EndCauses cause) {
        this.displayMessage(new Message() {
            @Override
            public MessageType getType() {
                return MessageType.NEWS;
            }

            @Override
            public String toString() {
                return cause.getExplanation();
            }
        });
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
        this.logger.info("Create Disease of Type: {}", type);
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
        logger.info("Completed creation of the new malattia: " + this.model.getDisease().toString());

    }

    @Override
    public void readDiseasesNames() {
        final DiseaseReader reader = new DiseaseReaderImpl();
        if (this.view != null) {
            this.view.setDiseasesData(reader.getDiseases());
        }
        this.diseaseController.readFile(reader.getDiseases());
    }

    @Override
    public Disease getDisease() {
        return this.model.getDisease();
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

    @Override
    public void displayMutationsName() {
        mutationController.displayMutationsName(this);
    }

    @Override
    public void setMutationsName(final List<String> list) {
        view.setMutationsName(List.copyOf(list), model.getInfo().getPoints());
    }

    @Override
    public void setMutationsDesc(final String description, final boolean activate, final int cost) {
        view.setMutationsDesc(description, activate, cost);
    }

    @Override
    public void displayMuatationDesc(final String name) {
        mutationController.displayMutationsDesc(name, this);
    }

    @Override
    public void update(final String name) {
        mutationController.update(name, model);
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
            logger.info("Start GameLoop");
            this.lock.lock();
            try {
                this.isRunning = true;
            } finally {
                this.lock.unlock();
            }
            while (!model.isGameOver()) {
                this.startTime = System.currentTimeMillis();
                this.render();
                this.update();
                this.remainingTime();

                this.lock.lock();
                try {
                    while (!this.isRunning) {
                        this.condition.await();
                    }
                } catch (InterruptedException e) {
                    logger.warn("Loop problem on await function: ", e);
                } finally {
                    this.lock.unlock();
                }
            }
            logger.info("Quitting GameLoop");
            quit();
        }

        private void update() {
            model.update();
        }

        private void render() {
            view.render();
            if (model.isGameOver()) {
                createAndDisplayMessage(model.getEndCause().get());
                view.quit();
            }
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

        public void startStop() {
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

        public boolean isRunning() {
            this.lock.lock();
            try {
                return this.isRunning;
            } finally {
                this.lock.unlock();
            }
        }
    }

    @Override
    public Map<TypeOfInfo, String> getInfoSingleRegion() {
        final Map<TypeOfInfo, String> info = new HashMap<>();
        final Optional<Region> r = this.model.getSelectedRegion();
        if (r.isPresent()) {
            info.put(TypeOfInfo.INFETTI, Long.toString(r.get().getNumInfected()));
            info.put(TypeOfInfo.MORTI, Long.toString(r.get().getNumDeath()));
            info.put(TypeOfInfo.REGION, r.get().getName());
        } else {
            final InfoData infoData = this.model.getInfo();
            info.put(TypeOfInfo.INFETTI, Long.toString(infoData.getTotalInfected()));
            info.put(TypeOfInfo.MORTI, Long.toString(infoData.getTotalDeaths()));
            info.put(TypeOfInfo.REGION, "Mondo");
        }
        return info;
    }

    @Override
    public void setRegions() {
        this.model.setRegions(regionController.getRegions());
    }
}
