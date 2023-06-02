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
    public void openInitialMenu(Stage stage);

    /**
     * 
     * @param stage
     *              the current stage.
     */
    public void openTutorial(Stage stage);

    /**
     * 
     * @param stage
     *              the current stage.
     */
    public void openDiseaseChoice(Stage stage);

    /**
     * 
     * @param stage
     *              the current stage.
     */
    public void openDiseaseName(Stage stage);

    /**
     * 
     * @param stage
     *              the current stage.
     */
    public void openBackScene(Stage stage);

    /**
     * 
     * @return
     *         the {@link SceneLoader}.
     */
    public SceneLoader getSceneLoader();
}
