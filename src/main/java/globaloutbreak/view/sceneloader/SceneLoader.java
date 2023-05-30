package globaloutbreak.view.sceneloader;

import javafx.stage.Stage;
import view.utilities.SceneStyle;

/**
 * An interface for loading scene.
 */
public interface SceneLoader {

    /**
     * @param SseneStyle
     *                   the scene to be loaded
     * @param stage
     *                   current stage
     */
    void loadScene(SceneStyle sceneStyle, Stage stage);

    void loadBackScene(Stage stage);
}
