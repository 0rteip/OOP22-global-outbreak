package globaloutbreak.view.scenecontroller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Class that manage button handlers.
 */
public final class MutationViewController extends AbstractSceneController implements SceneInitializer {
    @FXML
    private Button newGameButton;
    @FXML
    private Button tutorialButton;
    @FXML
    private GridPane buttonGridPane;
    @FXML
    private GridPane buttonGridPanell;
    @FXML
    private Button exitButton;
    @FXML
    private Button submitButton;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label pointLabel;
    @FXML
    private Button actionButton;
    @FXML
    private Label pointsText;
    private List<String> names;

    /**
     * Initialize.
     */
    @Override
    public void initializeScene() {
        this.getView().displayMutation();
        displayButton(this.getView().getMutations());
        displayPoints();
    }

    /**
     * display points.
     */
    private void displayPoints() {
        pointsText.setText("PUNTI " + this.getView().getPoints());
    }

    /**
     * display mutation button.
     * 
     * @param nameList
     */
    private void displayButton(final List<String> nameList) {
        int rowIndex = 0;
        int columnIndex = 0;
        names = nameList;
        for (int i = 0; i < nameList.size(); i++) {
            final Button button = createButton(nameList.get(i), i);
            buttonGridPane.add(button, columnIndex, rowIndex);
            columnIndex++;
            if (columnIndex == 4) {
                columnIndex = 0;
                rowIndex++;
            }
        }
    }

    private Button createButton(final String buttonText, final int index) {
        final Button button = new Button(buttonText);
        button.setOnAction(e -> handleButtonAction(buttonText, index));
        return button;
    }

    private Button createButtonActivate(final String buttonText, final int index) {
        final Button button = new Button(buttonText);
        button.setOnAction(e -> handleActionButtonAction(index));
        return button;
    }

    private void handleButtonAction(final String name, final int index) {
        this.getView().displayMutationDesc(name);
        descriptionLabel.setText(this.getView().getDescription());
        pointLabel.setText(this.getView().getCost());
        if (!this.getView().checkactivate()) {
            final Button button = createButtonActivate("Evolvi", index);
            buttonGridPanell.add(button, 0, 0);
        } else {
            final Button button = createButtonActivate("Involvi", index);
            buttonGridPanell.add(button, 0, 0);
        }

    }

    private void handleActionButtonAction(final int index) {
        this.getView().update(names.get(index));
    }

    /**
     * Go to the previous scene.
     * 
     * @param evt event handler
     */
    @FXML
    public void backScene(final MouseEvent evt) {
        this.getSceneManager().openMap();
    }
}
