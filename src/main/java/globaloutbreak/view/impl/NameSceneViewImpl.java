package globaloutbreak.view.impl;

import org.slf4j.Logger;

import globaloutbreak.view.api.SceneManager;
import globaloutbreak.view.api.SceneView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Class that manage button handlers.
 */
public class NameSceneViewImpl extends SceneView {

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
        if (this.stage == null) {
            this.stage = this.getStage(evt);
        }
        if (this.sceneManager != null) {
            sceneManager.goBack(stage);
        }
    }

    /**
     * Start the game.
     * 
     * @param evt event handler
     */
    @FXML
    public final void startGame(final MouseEvent evt) {

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
