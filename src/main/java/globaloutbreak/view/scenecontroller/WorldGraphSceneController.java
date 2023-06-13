package globaloutbreak.view.scenecontroller;

import globaloutbreak.model.infodata.InfoData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

/**
 * WorldGraphSceneController.
 */
public final class WorldGraphSceneController extends AbstractSceneController implements SceneInitializer {
    @FXML
    private PieChart pieChart;
    @FXML
    private Button worldButton;
    @FXML
    private Button cureButton;
    @FXML
    private Button backButton;

    @Override
    public void initializeScene() {
        final InfoData infodata = this.getView().getInfoData();
        final ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Morti", infodata.getTotalDeaths()),
                new PieChart.Data("Infetti", infodata.getTotalInfected()),
                new PieChart.Data("Sani",
                        infodata.getTotalPopulation() - infodata.getTotalDeaths() - infodata.getTotalInfected()));
        pieChart.setData(pieChartData);
        /*
         * ObservableList<PieChart.Data> pieChartData =
         * FXCollections.observableArrayList(
         * new PieChart.Data("Morti", 0),
         * new PieChart.Data("Infetti", 0),
         * new PieChart.Data("Sani", 7_000_000));
         * pieChart.setData(pieChartData);
         */

        for (final PieChart.Data data : pieChartData) {
            final String label = data.getName() + ": " + (long) data.getPieValue();
            data.setName(label);
        }
    }

    /**
     * Show Cure Progress.
     */
    @FXML
    public void showCureProgress() {
        this.getSceneManager().openCureGraph();
    }

    /**
     * Go back.
     */
    @FXML
    public void backScene() {
        this.getSceneManager().openMap();
    }

    /**
     * Show Word info.
     */
    @FXML
    public void showWorldInfo() {
    }
}
