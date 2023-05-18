package globaloutbreak.view.impl;

import java.io.IOException;

import org.slf4j.Logger;

import globaloutbreak.view.api.SceneManager;
import globaloutbreak.view.api.SceneView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Class that manage button handlers.
 */
public class StartSceneViewImpl extends SceneView {

    @FXML
    private Button newGameButton;

    @FXML
    private Button tutorialButton;

    @FXML
    private Button exitButton;

    private Stage stage;
    private SceneManager sceneManager;
    private Logger logger;

    /**
     * Initialize logger.
     */
    public final void initialize() {
        this.logger = getLogger();
    }

    /**
     * Go to choose disease name Gui.
     * 
     * @param evt
     */
    @FXML
    public final void chooseDiseaseName(final MouseEvent evt) {
        if (evt.getSource() instanceof Button && this.stage == null) {
            this.stage = this.getStage(evt);
        }
        try {
            if (this.sceneManager != null) {
                sceneManager.loadScreen("scelta nome", stage);
            }
        } catch (IOException e) {
            this.logger.warn("Error trying to load scene", e);
        }
    }

    /**
     * Go to tutorial Gui.
     * 
     * @param evt event handler
     */
    @FXML
    public final void openTutorial(final MouseEvent evt) {
        if (evt.getSource() instanceof Button && this.stage == null) {
            this.stage = getStage(evt);
        }
        try {
            if (this.sceneManager != null) {
                sceneManager.loadScreen("tutorial", stage);
            }
        } catch (IOException e) {
            this.logger.warn("Error trying to load scene", e);
        }
    }

    /**
     * Quit game.
     * 
     * @param evt
     */
    @FXML
    public final void quitGame(final MouseEvent evt) {
        Platform.exit();
    }

    /**
     * Set controller.
     * 
     * @param sceneManager
     */
    @Override
    public void setController(final SceneManager sceneManager) {
        if (sceneManager == null) {
            this.logger.warn("sceneManager cannot be null");
        }
        if (sceneManager instanceof SceneManagerImpl) {
            this.sceneManager = (SceneManagerImpl) sceneManager;
        } else {
            this.logger.warn("sceneManager must implemenet SceneManager");
        }
    }
}
