package globaloutbreak.view.impl;

import org.slf4j.Logger;

import globaloutbreak.view.api.SceneManager;
import globaloutbreak.view.api.AbstractSceneView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Class that manage button handlers.
 */
public class TutorialSceneViewImpl extends AbstractSceneView {

    @FXML
    private Button newGameButton;

    @FXML
    private Button tutorialButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button submitButton;

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
     * Go to the previous scene.
     * 
     * @param evt event handler
     */
    @FXML
    public final void backScene(final MouseEvent evt) {
        this.stage = this.stage == null ? this.getStage(evt) : this.stage;

        if (this.sceneManager != null) {
            sceneManager.goBack(stage);
        } else {
            this.logger.error("Error trying to load scene");
        }
    }

    /**
     * Set controller.
     * 
     * @param sceneManager
     */
    @Override
    public void setManager(final SceneManager sceneManager) {
        if (sceneManager == null) {
            this.logger.error("sceneManager cannot be null");
            throw new IllegalStateException("SceneManager is null. Cannot change scene.");
        }
        if (sceneManager instanceof SceneManagerImpl) {
            this.sceneManager = (SceneManagerImpl) sceneManager;
        } else {
            this.logger.error("sceneManager must implemenet SceneManager");
            throw new IllegalStateException("sceneManager must implemenet SceneManager.");
        }
    }
}
