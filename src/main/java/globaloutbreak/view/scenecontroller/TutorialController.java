package globaloutbreak.view.scenecontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Class that manage button handlers.
 */
public class TutorialController extends AbstractSceneController {

    @FXML
    private Button newGameButton;

    @FXML
    private Button tutorialButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button submitButton;

    /**
     * Initialize logger.
     */
    public final void initialize() {

    }

    /**
     * Go to the previous scene.
     * 
     * @param evt event handler
     */
    @FXML
    public final void backScene(final MouseEvent evt) {
        this.getSceneManager().openBackScene();
    }
}
