package globaloutbreak.view;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.controller.Controller;
import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.api.Voyage;
import globaloutbreak.settings.windowsettings.WindowSettings;
import globaloutbreak.settings.windowsettings.WindowSettingsImpl;
import globaloutbreak.view.scenefactory.SceneAdministrator;
import globaloutbreak.view.scenefactory.SceneAdministratorImpl;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Implementation of View.
 */
public final class ViewImpl implements View {

    // private final Stage stage;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WindowSettings settings = new WindowSettingsImpl();
    private final SceneAdministrator administrator;
    private Controller controller;

    /**
     * Creates a view.
     * 
     * @param primaryStage controller
     */
    public ViewImpl(final Stage primaryStage) {
        this.administrator = new SceneAdministratorImpl(primaryStage, this);
    }

    @Override
    public void start(final Controller controller) {
        this.controller = controller;
        this.administrator.openMapScene();
    }

    @Override
    public void displayInfo(final Infodata info) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayInfo'");
    }

    @Override
    public void displayMessage(final Message message) {
        Platform.runLater(() -> this.administrator.openMessage(message));
        this.controller.startStop();
    }

    @Override
    public void displayVoyage(final Voyage voyage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayVoyage'");
    }

    @Override
    public WindowSettings getWindowSettings() {
        return this.settings;
    }

    @Override
    public SceneAdministrator getSceneAdministrator() {
        return this.administrator;
    }

    @Override
    public boolean isGameRunning() {
        return this.controller.isGameRunning();
    }

    @Override
    public void startStop() {
        this.controller.startStop();
    }

    @Override
    public GameSpeed getGameSpeed() {
        return this.controller.getSettings().getGameSpeed();
    }

    @Override
    public List<GameSpeed> getGameSpeeds() {
        return this.controller.getSettings().getGameSpeeds();
    }

    @Override
    public void setGameSpeed(final GameSpeed gameSpeed) {
        this.controller.setGameSpeed(gameSpeed);
    }

    @Override
    public ViewImpl clone() {
        try {
            return (ViewImpl) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.warn("Clone not supported for view", e);
            return this;
        }
    }
}
