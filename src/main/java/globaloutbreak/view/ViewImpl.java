package globaloutbreak.view;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.controller.Controller;
import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.api.Voyage;
import globaloutbreak.model.disease.DiseaseData;
import globaloutbreak.view.scenemanager.SceneManager;
import globaloutbreak.view.scenemanager.SceneManagerImpl2;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import settings.WindowSettings;
import settings.WindowSettingsImpl;

/**
 * Class ViewImpl.
 */
public final class ViewImpl implements View {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WindowSettings settings = new WindowSettingsImpl();
    private final SceneManager manager;
    private final List<Button> diseasesButtons = new ArrayList<>();
    private Controller controller;

    /**
     * Creates a VIewImpl.
     * 
     * @param stage
     *              stage
     */
    public ViewImpl(final Stage stage) {
        this.manager = new SceneManagerImpl2(stage, this);
    }

    @Override
    public void start(final Controller controller) {
        this.controller = controller;
        this.manager.openInitialMenu();
    }

    @Override
    public void displayInfo(final Infodata info) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayInfo'");
    }

    @Override
    public void displayMessage(final Message message) {
        Platform.runLater(() -> this.manager.openMessage(message));
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
    public SceneManager getSceneManager() {
        return this.manager;
    }

    @Override
    public List<Button> getDiseasesButtons() {
        return List.copyOf(diseasesButtons);
    }

    @Override
    public void setDiseasesData(final List<DiseaseData> diseasesNames) {
        diseasesNames.stream().forEach(disease -> diseasesButtons.add(new Button(disease.getType())));
    }

    @Override
    public void choosenDisease(final String type) {
        this.controller.createDisease(type);
    }

    @Override
    public void choosenNameDisease(final String name) {
        this.controller.choosenDiseaseName(name);
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

    @Override
    public void readDiseasesNames() {
        this.controller.readDiseasesNames();
    }

    @Override
    public void quit() {
        this.controller.quit();
    }

}
