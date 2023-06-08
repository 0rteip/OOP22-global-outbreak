package globaloutbreak.view.scenecontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class GeneralGraphsSceneController extends AbstractSceneController implements SceneInitializer {
    @FXML
    private PieChart pieChart;

    @Override
    public void initializeScene() {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Morti", 0),
                new PieChart.Data("Infetti", 0),
                new PieChart.Data("Sani", 7_000_000));
        pieChart.setData(pieChartData);

        for (PieChart.Data data : pieChartData) {
            String label = data.getName() + ": " + (int) data.getPieValue();
            data.setName(label);
        }
    }

}
