package globaloutbreak.view.scenecontroller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller Choose Disease scene.
 */
public final class ChooseDiseaseController extends AbstractSceneController implements SettingsInitializer {

    private static final int SMALL_SPACING = 50;
    private static final int BIG_SPACING = 100;

    private String selectedType;
    private Stage stage;

    private boolean consumed;

    @FXML
    private VBox chooseDiseaseVbox;

    @FXML
    private HBox chooseDiseaseHbox;

    @FXML
    private Button backSceneButton;

    @Override
    public void initializeSettings() {
        if (!consumed) {
            chooseDiseaseVbox.setSpacing(SMALL_SPACING);
            chooseDiseaseHbox.setAlignment(Pos.CENTER);
            chooseDiseaseHbox.setSpacing(BIG_SPACING);
            this.getView().readDiseasesNames();
            final List<Button> diseasesNames = this.getView().getDiseasesButtons();
            diseasesNames.stream().forEach(button -> {
                button.setMinHeight(BIG_SPACING);
                button.setMinWidth(BIG_SPACING);
                button.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"
                        + "-fx-text-fill: white;"
                        + "-fx-font-size: 18px;"
                        + "-fx-font-weight: bold;");
                button.setOnMouseClicked(event -> {
                    this.selectedType = button.getText();
                    this.changeScene(event);
                });
            });
            chooseDiseaseHbox.getChildren().addAll(diseasesNames);
            consumed = true;
        }
    }

    /**
     * return to the prec scene.
     * 
     * @param evt
     */
    @FXML
    public void backScene(final MouseEvent evt) {
        this.stage = this.stage == null ? this.getStage(evt) : this.stage;
        this.getSceneManager().openBackScene(stage);
    }

    /**
     * Go to the next Scene.
     * 
     * @param evt
     */
    private void changeScene(final MouseEvent evt) {
        this.stage = this.stage == null ? this.getStage(evt) : this.stage;
        this.getView().choosenDisease(this.selectedType);
        this.getSceneManager().openDiseaseName(stage);
    }
}
