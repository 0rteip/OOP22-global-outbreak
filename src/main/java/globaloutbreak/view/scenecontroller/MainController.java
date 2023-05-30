package globaloutbreak.view.scenecontroller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    private Stage stage;

    /**
     * Initialize logger.
     */
    public final void initialize() {

    }

    /**
     * Go to choose disease name Gui.
     * 
     * @param evt
     */
    @FXML
    public final void chooseDiseaseName(final MouseEvent evt) {
        if (evt.getSource() instanceof Button && this.stage == null) {
            this.stage = this.getStage(evt);
        }
        this.getSceneManager().openDiseaseName(stage);
    }

    /**
     * Go to tutorial Gui.
     * 
     * @param evt event handler
     */
    @FXML
    public final void openTutorial(final MouseEvent evt) {
        if (evt.getSource() instanceof Button && this.stage == null) {
            this.stage = getStage(evt);
        }
        this.getSceneManager().openTutorial(stage);
    }

    /**
     * Quit game.
     * 
     * @param evt
     */
    @FXML
    public final void quitGame(final MouseEvent evt) {
        Platform.runLater(() -> {
            Platform.exit();
        });
    }
}
