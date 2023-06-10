package globaloutbreak.view.scenecontroller;

import globaloutbreak.model.infodata.InfoData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

public class WorldGraphSceneController extends AbstractSceneController implements SceneInitializer {
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
        InfoData infodata = this.getView().getInfoData();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Morti", infodata.getTotalDeaths()),
                new PieChart.Data("Infetti", infodata.getTotalInfected()),
                new PieChart.Data("Sani", infodata.getTotalPopulation()));
        pieChart.setData(pieChartData);
        /*ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Morti", 0),
                new PieChart.Data("Infetti", 0),
                new PieChart.Data("Sani", 7_000_000));
        pieChart.setData(pieChartData);

        for (PieChart.Data data : pieChartData) {
            String label = data.getName() + ": " + (int) data.getPieValue();
            data.setName(label);
        }*/
    }

    @FXML
    public void showCureProgress() {
        this.getSceneManager().openCureGraphScene();
    }

    @FXML
    public void backScene() {
        this.getSceneManager().openMap();
    }

    @FXML
    public void showWorldInfo() {
    }
}
