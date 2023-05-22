package globaloutbreak.view.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Abstract class that manage menu gui buttons.
 */
public abstract class AbstractSceneView {

    /**
     * Logger declaration.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Set controller.
     * 
     * @param sceneManager
     */
    public abstract void setManager(SceneManager sceneManager);

    /**
     * Get the current Stage.
     * 
     * @param evt
     * @return return the current stage
     */
    protected Stage getStage(final MouseEvent evt) {
        final Button source = (Button) evt.getSource();
        final Window window = source.getScene().getWindow();

        try {
            return (Stage) window;
        } catch (ClassCastException e) {
            this.logger.error("The window is not an instance of Stage.");
            return null;
        }
    }

    /**
     * get logger object.
     * 
     * @return a logger
     */
    protected Logger getLogger() {
        return this.logger != null ? this.logger : LoggerFactory.getLogger(AbstractSceneView.class);
    }
}
