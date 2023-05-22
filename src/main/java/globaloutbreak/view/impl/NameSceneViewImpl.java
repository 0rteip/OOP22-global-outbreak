package globaloutbreak.view.impl;

import org.slf4j.Logger;

import globaloutbreak.view.api.SceneManager;
import globaloutbreak.view.api.AbstractSceneView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Class that manage button handlers (Choose name scene).
 */
public class NameSceneViewImpl extends AbstractSceneView {

    @FXML
    private Button submitButton;

    private Stage stage;
    private SceneManager sceneManager;
    private Logger logger;

    /**
     * Initialize logger.
     */
    public void initialize() {
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
    public void setManager(final SceneManager sceneManager) {
        if (sceneManager == null) {
            this.logger.warn("sceneManager cannot be null");
            return;
        }
        if (!(sceneManager instanceof SceneManagerImpl)) {
            this.logger.warn("sceneManager must implemenet SceneManager");
            return;
        }

        this.sceneManager = (SceneManagerImpl) sceneManager;
    }
}
