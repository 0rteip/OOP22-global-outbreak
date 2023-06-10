package globaloutbreak.view.scenecontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import globaloutbreak.model.disease.DiseaseData;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller Choose Disease scene.
 */
public final class ChooseDiseaseController extends AbstractSceneController implements SceneInitializer {

    private static final int SMALL_SPACING = 50;
    private static final int BIG_SPACING = 100;
    private List<Button> buttonsList = new ArrayList<>();
    private Optional<String> buttonClicked = Optional.empty();
    private List<Label> labelsList = new ArrayList<>();

    private String selectedType;

    @FXML
    private VBox chooseDiseaseVbox;

    @FXML
    private VBox vboxParameters;

    @FXML
    private HBox chooseDiseaseHbox;

    @FXML
    private Button backSceneButton;

    @Override
    public void initializeScene() {
        this.vboxParameters.setVisible(false);
        if (chooseDiseaseHbox.getChildren().isEmpty()) {
            chooseDiseaseVbox.setSpacing(SMALL_SPACING);
            chooseDiseaseHbox.setAlignment(Pos.CENTER);
            chooseDiseaseHbox.setSpacing(BIG_SPACING);
            final List<DiseaseData> diseasesNames = this.getView().getDiseasesDatas();
            diseasesNames.stream().forEach(data -> {
                Button button = new Button(data.getType());
                button.setMinHeight(BIG_SPACING);
                button.setMinWidth(BIG_SPACING);
                button.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"
                        + "-fx-text-fill: white;"
                        + "-fx-font-size: 18px;"
                        + "-fx-font-weight: bold;");
                button.setOnMouseClicked(event -> {
                    if (buttonClicked.isPresent() && buttonClicked.get().equals(button.getText())) {
                        this.selectedType = button.getText();
                        this.openDiseaseNameScene();
                        this.buttonClicked = Optional.empty();
                        vboxParameters.getChildren().clear();
                    } else {
                        if (!labelsList.isEmpty()) {
                            labelsList = new ArrayList<>();
                        }
                        labelsList.add(new Label("Infettività: " + data.getInfectivity()));
                        labelsList.add(new Label("Letalità: " + data.getLethality()));
                        labelsList.add(new Label("Infettività nell'aria: " + data.getAirInfectivity()));
                        labelsList.add(new Label("Infettività nell'acqua: " + data.getSeaInfectivity()));
                        labelsList.add(new Label("Infettività nella terra: " + data.getLandInfectivity()));
                        labelsList.add(new Label("Infettività nei paesi caldi: " + data.getHeatInfectivity()));
                        labelsList.add(new Label("Infettività nei paesi freddi: " + data.getColdInfectivity()));
                        labelsList.add(new Label("Infettività nei paesi umidi: " + data.getHumidityInfectivity()));
                        labelsList.add(new Label("Infettività nei paesi aridi: " + data.getAridityInfectivity()));
                        labelsList.add(new Label("Infettività nei paesi poveri: " + data.getPovertyInfectivity()));
                        labelsList.add(new Label("Resistenza alla cura: " + data.getCureResistance()));

                        vboxParameters.getChildren().addAll(labelsList);
                        this.buttonClicked = Optional.of(button.getText());
                        this.vboxParameters.setVisible(true);
                    }
                });
                buttonsList.add(button);
            });
            chooseDiseaseHbox.getChildren().addAll(buttonsList);
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
