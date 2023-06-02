package globaloutbreak.view.sceneloader;

import globaloutbreak.view.scenecontroller.SceneController;
import javafx.stage.Stage;
import view.utilities.SceneStyle;

/**
 * An interface for loading scene.
 */
public interface SceneLoader {

    /**
     * @param sceneStyle
     *                   the scene to be loaded
     * @param stage
     *                   current stage
     */
    void loadScene(SceneStyle sceneStyle, Stage stage);

    /**
     * 
     * @param stage
     *              current Stage
     */
    void loadBackScene(Stage stage);

    /**
     * Load scenes into sceneMap.
     */
    void loadFiles();

    /**
     * Get the scene controller.
     * 
     * @param sceneStyle
     *                   the scene.
     * @return
     *         the scene controller.
     */
    SceneController getController(SceneStyle sceneStyle);
}
