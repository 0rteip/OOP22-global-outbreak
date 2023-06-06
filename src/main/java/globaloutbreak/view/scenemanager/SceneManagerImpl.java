package globaloutbreak.view.scenemanager;

import globaloutbreak.view.View;
import globaloutbreak.view.sceneloader.SceneLoader;
import globaloutbreak.view.sceneloader.SceneLoaderImpl;
import javafx.stage.Stage;
import view.utilities.SceneStyle;

/**
 * Class that manage the scene changing.
 */
public final class SceneManagerImpl implements SceneManager {

    private final SceneLoader sceneLoader;

    /**
     * Constructor that load menu scenes.
     * 
     * @param view
     * 
     */
    public SceneManagerImpl(final View view) {
        this.sceneLoader = new SceneLoaderImpl(view);
    }

    /**
     * Load the main Scene.
     * 
     * @param stage
     *              the current stage
     */
    @Override
    public void openInitialMenu(final Stage stage) {
        this.openScene(SceneStyle.INITIALMENU, stage);
    }

    /**
     * Load the tutorial Scene.
     * 
     * @param stage
     *              the current stage
     */
    @Override
    public void openTutorial(final Stage stage) {
        this.openScene(SceneStyle.TUTORIAL, stage);
    }

    /**
     * Load the main Scene.
     * 
     * @param stage
     *              the current Stage
     */
    @Override
    public void openDiseaseChoice(final Stage stage) {
        this.openScene(SceneStyle.CHOOSEDISEASE, stage);
    }

    @Override
    public void openDiseaseName(final Stage stage) {
        this.openScene(SceneStyle.DISEASENAME, stage);
    }

    /**
     * Set the previous scene.
     * 
     * @param stage
     *              the current stage
     */
    @Override
    public void openBackScene(final Stage stage) {
        this.sceneLoader.loadBackScene(stage);
    }

    /**
     * Open the scene selected.
     * 
     * @param sceneStyle
     *                   the scene to be loaded.
     * @param stage
     *                   the current stage
     */
    private void openScene(final SceneStyle sceneStyle, final Stage stage) {
        this.sceneLoader.loadScene(sceneStyle, stage);
    }

    /**
     * @return
     *         the scene loader.
     */
    @Override
    public SceneLoader getSceneLoader() {
        return this.sceneLoader;
    }
}
