package globaloutbreak.view.scenecontroller;

import globaloutbreak.view.View;
import globaloutbreak.view.scenemanager.SceneManager;

/**
 * Abstract class that manage menu gui buttons.
 */
public class AbstractSceneController implements SceneController {

    private SceneManager sceneManager;
    private View view;

    /**
     * Set controller.
     * 
     * @param sceneManager
     */
    @Override
    public final void setSceneManager(final SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * return the scene manager.
     */
    @Override
    public final SceneManager getSceneManager() {
        return this.sceneManager;
    }

    /**
     * set View.
     */
    @Override
    public final void setView(final View view) {
        this.view = view;
    }

    /**
     * return View.
     */
    @Override
    public final View getView() {
        return this.view;
    }
}
