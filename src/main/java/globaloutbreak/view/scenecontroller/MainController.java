package globaloutbreak.view.scenecontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Class that manage button handlers.
 */
public class MainController extends AbstractSceneController {

    @FXML
    private Button newGameButton;

    @FXML
    private Button tutorialButton;

    @FXML
    private Button exitButton;

    /**
     * Initialize logger.
     */
    public final void initialize() {

    }

    /**
     * Go to choose disease name Gui.
     */
    @FXML
    public final void chooseDisease() {
        this.getSceneManager().openDiseaseChoice();
    }

    /**
     * Go to tutorial Gui.
     */
    @FXML
    public final void openTutorial() {
        this.getSceneManager().openTutorial();
    }

    /**
     * Quit game.
     */
    @FXML
    public final void quitGame() {
        this.getView().quit();
    }
}
