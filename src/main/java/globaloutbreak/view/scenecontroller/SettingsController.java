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
public final class SettingsController extends AbstractSceneController implements SceneInitializer {

    @FXML
    private ComboBox<GameSpeed> gameSpeedComboBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    @FXML
    private void exitSettings() {
        this.getSceneManager().openMap();
        this.getView().startStop();
    }

    @FXML
    private void saveSettings() {
        this.pcs.firePropertyChange("gameSpeed",
                this.getView().getGameSettings().getGameSpeed(),
                this.gameSpeedComboBox.getSelectionModel().getSelectedItem());
        this.exitSettings();
    }

    @Override
    public void initializeScene() {
        if (this.getView().isGameRunning()) {
            this.getView().startStop();
        }
        if (this.gameSpeedComboBox.getItems().isEmpty()) {
            this.gameSpeedComboBox.getItems().addAll(this.getView().getGameSettings().getGameSpeeds());
            this.gameSpeedComboBox.getSelectionModel()
                    .select(this.getView().getGameSettings().getGameSpeed());
            this.pcs.addPropertyChangeListener(new GameSpeedObserver(this.getView()));
        }
    }
}
