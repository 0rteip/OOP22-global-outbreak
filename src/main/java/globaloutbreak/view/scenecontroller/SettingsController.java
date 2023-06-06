package globaloutbreak.view.scenecontroller;

import java.beans.PropertyChangeSupport;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.gamespeed.GameSpeedObserver;
import javafx.scene.control.Button;

/**
 * Scene for settings.
 */
public final class SettingsController extends AbstractSceneController implements SettingsInitializer {

    @FXML
    private ComboBox<GameSpeed> gameSpeedComboBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    @FXML
    private void exitSettings() {
        this.getSceneAdministrator().openMapScene();

        this.getSceneAdministrator().getView().startStop();

    }

    @FXML
    private void saveSettings() {
        this.pcs.firePropertyChange("gameSpeed",
                this.getSceneAdministrator().getView().getGameSpeed(),
                this.gameSpeedComboBox.getSelectionModel().getSelectedItem());
        this.exitSettings();
    }

    @Override
    public void initializeSettings() {
        if (this.gameSpeedComboBox.getItems().isEmpty()) {
            this.gameSpeedComboBox.getItems().addAll(this.getSceneAdministrator().getView().getGameSpeeds());
            this.gameSpeedComboBox.getSelectionModel()
                    .select(this.getSceneAdministrator().getView().getGameSpeed());
            this.pcs.addPropertyChangeListener(new GameSpeedObserver(this.getSceneAdministrator().getView()));
        }
    }
}
