package globaloutbreak.view.scenecontroller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import globaloutbreak.view.View;
import globaloutbreak.view.scenemanager.SceneManager;

/**
 * Abstract class that manage menu gui buttons.
 */
public class AbstractSceneController implements SceneController {

    private SceneManager sceneManager;
    private View view;

    @Override
    public final void setSceneManager(final SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public final SceneManager getSceneManager() {
        return this.sceneManager;
    }

    // @formatter:off
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "We need to use the correct instance of View"
    )
    // @formatter:on
    @Override
    public final void setView(final View view) {
        this.view = view;
    }

    // @formatter:off
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "We need to use the correct instance of View to let Scene Controller to call method on the view"
    )
    // @formatter:on
    @Override
    public final View getView() {
        return this.view;
    }
}
