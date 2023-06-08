package globaloutbreak.controller;

// import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import globaloutbreak.diseasereader.DiseaseReader;
import globaloutbreak.diseasereader.DiseaseReaderImpl;
import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.Model;
import globaloutbreak.model.ModelImpl;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.message.MessageType;
import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.voyage.Voyage;
import globaloutbreak.model.infodata.Infodata;
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
    public void updateInfo(final Infodata info) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateInfo'");
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
        this.settings.setGameSpeed(gameSpeed);
    }

    @Override
    public void startStop() {
        if (!this.gameLoop.isAlive()) {
            System.out.println("START loop");
            this.gameLoop.start();
        } else {
            System.out.println(this.gameLoop.isRunning ? "STOP loop" : "RESTART loop");
            this.gameLoop.startStop();
        }
    }

    @Override
    public boolean isGameRunning() {
        return this.gameLoop.isRunning;
    }

    // // @formatter:off
    // @SuppressFBWarnings(
    // value = "EI_EXPOSE_REP",
    // justification = "settings is casted to a only getters interface and
    // considerable immutable"
    // )
    // // @formatter:on
    @Override
    public GameSettingsGetter getSettings() {
        return this.settings.clone();
    }

    @Override
    public void choosenDisease(final String type) {
    }

    @Override
    public void choosenDiseaseName(final String name) {
        System.out.println("Disease: " + name);
        this.view.displayMessage(new Message() {

            @Override
            public MessageType getType() {
                return MessageType.NEWS;
            }

            @Override
            public String toString() {
                return "Create " + name;
            }
        });
    }

    @Override
    public void createDisease(final String type) {
        System.out.println("Disease: " + type);
    }

    @Override
    public void readDiseasesNames() {
        final DiseaseReader reader = new DiseaseReaderImpl();
        this.view.setDiseasesData(reader.getDiseases());
    }

    @Override
    public void quit() {
        Platform.exit();
    }

    private final class GameLoop extends Thread {

        private volatile boolean isRunning;
        private long startTime;
        private final Object mutex = new Object();

        GameLoop() {
            this.setDaemon(true);
        }

        @Override
        public void run() {
            this.isRunning = true;
            while (this.isRunning && !model.isGameOver()) {
                startTime = System.currentTimeMillis();
                this.update();
                this.render();
                this.remainingTime();

                synchronized (this.mutex) {
                    try {
                        if (!this.isRunning) {
                            this.mutex.wait();
                        }
                    } catch (InterruptedException e) {
                        logger.warn("Loop problem on wait function:", e);
                    }
                }
            }
        }

        private void update() {
            // System.out.println("update");
            // ControllerImpl.this.update(dayCicle);
            // if (ControllerImpl.this.model.isSimulationOver()) {
            // ControllerImpl.this.view.setSimulationOver();
            // }
        }

        private void render() {
            System.out.println("view: " + view + "\ngames: " + settings.getGameSpeed());
            view.displayMessage(new Message() {

                @Override
                public MessageType getType() {
                    return MessageType.CURE;
                }

            });
            // System.out.println("reder");
            // System.out.println(LocalTime.now());

            // ControllerImpl.this.view.render(ControllerImpl.this.model.getFoods(),
            // ControllerImpl.this.model.getOrganisms());
        }

        private void remainingTime() {
            final long elapsedTime = System.currentTimeMillis() - startTime;
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
            this.isRunning = !this.isRunning;
            synchronized (this.mutex) {
                if (this.isRunning) {
                    this.mutex.notifyAll();
                }
            }
        }

    }
}
