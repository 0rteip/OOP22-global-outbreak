package globaloutbreak.view.scenecontroller;

import globaloutbreak.view.View;
import globaloutbreak.view.scenemanager.SceneManager;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Interface of a generic Scene.
 */
public interface SceneController {

    /**
     * @param sceneManager
     *                           the {@link SceneManager} to use
     */
    void setSceneManager(SceneManager sceneManager);

    /**
     * @return
     *         the {@link SceneManager}
     */
    SceneManager getSceneManager();

    /**
     * @param view
     *             set current {@link View}
     */
    void setView(View view);

    /**
     * @return
     *         the {@link View}
     */
    View getView();

    /**
     * Get the current stage.
     * 
     * @param event
     *              event
     * @return
     *         satge
     */
    Stage getStage(MouseEvent event);
}
