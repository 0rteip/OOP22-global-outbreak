package globaloutbreak.view.scenemanager;

import globaloutbreak.view.sceneloader.SceneLoader;
import javafx.stage.Stage;

/**
 * Interface that manage the javaFX scenes.
 */
public interface SceneManager {

    /**
     * 
     * @param stage
     *              the current stage.
     */
    void openInitialMenu(Stage stage);

    /**
     * 
     * @param stage
     *              the current stage.
     */
    void openTutorial(Stage stage);

    /**
     * 
     * @param stage
     *              the current stage.
     */
    void openDiseaseChoice(Stage stage);

    /**
     * 
     * @param stage
     *              the current stage.
     */
    void openDiseaseName(Stage stage);

    /**
     * 
     * @param stage
     *              the current stage.
     */
    void openBackScene(Stage stage);

    /**
     * 
     * @return
     *         the {@link SceneLoader}.
     */
    SceneLoader getSceneLoader();
}
