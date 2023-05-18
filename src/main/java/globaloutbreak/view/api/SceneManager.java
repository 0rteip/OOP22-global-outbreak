package globaloutbreak.view.api;

import java.io.IOException;

import javafx.stage.Stage;

/**
 * Interface that manage the javaFX scenes.
 */
public interface SceneManager {

    /**
     * Load a new scene.
     * 
     * @param fxmlPath FXML scene filepath
     * @param stage    current stage
     * @throws IOException
     */
    void loadScreen(String fxmlPath, Stage stage) throws IOException;

    /**
     * Go to prec Screen.
     * 
     * @param stage current stage
     */
    void goBack(Stage stage);
}
