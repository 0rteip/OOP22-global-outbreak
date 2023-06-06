package globaloutbreak.view.scenecontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.view.View;
import globaloutbreak.view.scenemanager.SceneManager;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Abstract class that manage menu gui buttons.
 */
public class AbstractSceneController implements SceneController {

    private SceneManager sceneManager;
    private View view;
    /**
     * Logger declaration.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    /**
     * Get the current Stage.
     * 
     * @param evt
     * @return return the current stage
     */
    public Stage getStage(final MouseEvent evt) {
        final Button source = (Button) evt.getSource();
        final Window window = source.getScene().getWindow();

        try {
            return (Stage) window;
        } catch (ClassCastException e) {
            this.logger.error("The window is not an instance of Stage.");
            return null;
        }
    }
}
