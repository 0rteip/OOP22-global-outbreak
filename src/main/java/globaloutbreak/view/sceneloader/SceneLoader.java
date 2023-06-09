package globaloutbreak.view.sceneloader;

import globaloutbreak.model.message.Message;
import globaloutbreak.view.utilities.SceneStyle;
import javafx.stage.Stage;

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
     * Open a dialog.
     * 
     * @param stage
     *                stage
     * @param message
     *                message
     */
    void openDialog(Stage stage, Message message);
}
