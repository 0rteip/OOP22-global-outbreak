package globaloutbreak.view.scenecontroller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller Choose Disease scene.
 */
public final class ChooseDiseaseController extends AbstractSceneController implements SceneInitializer {

    private static final int SMALL_SPACING = 50;
    private static final int BIG_SPACING = 100;

    private String selectedType;

    @FXML
    private VBox chooseDiseaseVbox;

    @FXML
    private HBox chooseDiseaseHbox;

    @FXML
    private Button backSceneButton;

    @Override
    public void initializeScene() {
        if (chooseDiseaseHbox.getChildren().isEmpty()) {
            chooseDiseaseVbox.setSpacing(SMALL_SPACING);
            chooseDiseaseHbox.setAlignment(Pos.CENTER);
            chooseDiseaseHbox.setSpacing(BIG_SPACING);
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

                    this.openDiseaseNameScene();
                });
            });
            chooseDiseaseHbox.getChildren().addAll(diseasesNames);
        }
    }

    /**
     * return to the prec scene.
     */
    @FXML
    public void backScene() {
        this.getSceneManager().openInitialMenu();
    }

    /**
     * Go to the next Scene.
     */
    private void openDiseaseNameScene() {
        this.getView().choosenDisease(this.selectedType);
        this.getSceneManager().openDiseaseName();
    }
}
