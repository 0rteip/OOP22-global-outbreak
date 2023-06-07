package globaloutbreak.view.scenecontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Class that manage button handlers (Choose name scene).
 */
public class DiseaseNameController extends AbstractSceneController {

    @FXML
    private TextField nameTextField;
    @FXML
    private Button submitButton;

    private Stage stage;

    /**
     * Initialize logger.
     */
    public void initialize() {

    }

    /**
     * Go to the previous scene.
     * 
     * @param evt event handler
     */
    @FXML
    public final void backScene(final MouseEvent evt) {
        this.stage = this.stage == null ? this.getStage(evt) : this.stage;

        this.getSceneManager().openBackScene(stage);
    }

    /**
     * Start the game.
     * 
     * @param evt event handler
     */
    @FXML
    public final void startGame(final MouseEvent evt) {
        this.getView().choosenNameDisease(nameTextField.getText());
    }

}
