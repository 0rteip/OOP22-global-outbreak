package globaloutbreak.view.scenecontroller;

import java.util.ArrayList;
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
public class ChooseDiseaseController extends AbstractSceneController implements SettingsInitializer {

    private List<Button> diseasesNames = new ArrayList<>();

    private Stage stage;

    @FXML
    private VBox chooseDiseaseVbox;
    @FXML
    private HBox chooseDiseaseHbox;

    @FXML
    private Button backSceneButton;

    /**
     * Initilize Button for each Disease.
     */
    @Override
    public void initializeSettings() {

        chooseDiseaseVbox.setSpacing(50);
        chooseDiseaseHbox.setAlignment(Pos.CENTER);
        chooseDiseaseHbox.setSpacing(100);
        this.getView().getController().readDiseasesNames();
        diseasesNames = this.getView().getDiseasesButtons();
        diseasesNames.stream().forEach(button -> {
            button.setMinHeight(100);
            button.setMinWidth(100);
            button.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"
                    + "-fx-text-fill: white;"
                    + "-fx-font-size: 18px;"
                    + "-fx-font-weight: bold;");
            button.setOnMouseClicked(event -> {
                String selectedDisease = button.getText();
                System.out.println("Hai selezionato la malattia: " + selectedDisease);
                this.changeScene(event);
            });
        });
        chooseDiseaseHbox.getChildren().addAll(diseasesNames);
    }

    /**
     * return to the prec scene.
     * 
     * @param evt
     */
    @FXML
    public final void backScene(final MouseEvent evt) {
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

        this.getSceneManager().openDiseaseName(stage);
    }
}
