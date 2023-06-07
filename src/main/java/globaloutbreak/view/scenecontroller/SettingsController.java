package globaloutbreak.view.scenecontroller;

import java.beans.PropertyChangeSupport;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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

    private Stage stage;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    @FXML
    private void exitSettings(final MouseEvent event) {
        stage = stage == null ? this.getStage(event) : stage;
        this.getSceneManager().openBackScene(stage);
        this.getView().startStop();

    }

    @FXML
    private void saveSettings(final MouseEvent event) {
        this.pcs.firePropertyChange("gameSpeed",
                this.getView().getGameSpeed(),
                this.gameSpeedComboBox.getSelectionModel().getSelectedItem());
        this.exitSettings(event);
    }

    @Override
    public void initializeSettings() {
        if (this.gameSpeedComboBox.getItems().isEmpty()) {
            this.gameSpeedComboBox.getItems().addAll(this.getView().getGameSpeeds());
            this.gameSpeedComboBox.getSelectionModel()
                    .select(this.getView().getGameSpeed());
            this.pcs.addPropertyChangeListener(new GameSpeedObserver(this.getView()));
        }
    }
}
