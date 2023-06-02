package globaloutbreak.view.scenecontroller;

import globaloutbreak.view.View;
import globaloutbreak.view.scenemanager.SceneManager;

/**
 * Interface of a generic Scene.
 */
public interface SceneController {

    /**
     * @param sceneAdministrator
     *                           the {@link SceneManager} to use
     */
    void setSceneManager(SceneManager sceneAdministrator);

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
