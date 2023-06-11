package globaloutbreak.view.scenecontroller;

import globaloutbreak.model.infodata.InfoData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

/**
 * Controller for CureGraph Scene.
 */
public final class CureGraphSceneController extends AbstractSceneController implements SceneInitializer {

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button worldButton;
    @FXML
    private Button cureButton;
    @FXML
    private Label progress;
    @FXML
    private TextField cureContributors;

    @Override
    public void initializeScene() {
        final InfoData infoData = this.getView().getInfoData();
        final int percentage = infoData.getCureData().getProgress();
        this.progressBar.setProgress(Double.valueOf(percentage) / 100);
        this.progress.setText(Integer.toString(percentage) + " %");
        infoData.getCureData().getMajorContributors().stream().map(region -> region.getName()).toList();
        final String listContributors = String.join(",",
                infoData.getCureData().getMajorContributors().stream().map(region -> region.getName()).toList());
        this.cureContributors.setText(listContributors);
    }

    /**
     * Show info.
     */
    @FXML
    public void showWorldInfo() {
        this.getSceneManager().openWorldGraph();
    }

    /**
     * Show progress.
     */
    @FXML
    public void showCureProgress() {
    }

    /**
     * Go back.
     */
    @FXML
    public void backScene() {
        this.getSceneManager().openMap();
    }
}
