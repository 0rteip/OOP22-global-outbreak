package globaloutbreak.view.scenecontroller;

import java.util.Optional;

import globaloutbreak.model.message.Message;
import globaloutbreak.view.messagedialog.MessageDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;

/**
 * Main page controller.
 */
public final class MainController extends AbstractSceneController {

    @FXML
    private Button settingsButton;

    @FXML
    private Button stopButton;

    @FXML
    private void openSettings() {
        if (this.getSceneAdministrator().getView().isGameRunning()) {
            this.getSceneAdministrator().getView().startStop();
        }
        this.getSceneAdministrator().openSettingsScene();
    }

    @FXML
    private void startStop() {
        

        this.getSceneAdministrator().getView().startStop();
    }
}
