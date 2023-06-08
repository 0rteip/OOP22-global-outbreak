package globaloutbreak.view.scenecontroller;

import globaloutbreak.view.View;
import globaloutbreak.view.scenemanager.SceneManager;

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
}
