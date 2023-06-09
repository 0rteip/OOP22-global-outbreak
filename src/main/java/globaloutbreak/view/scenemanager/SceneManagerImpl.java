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
    private final Stage stage;

    /**
     * Constructor that load menu scenes.
     * 
     * @param stage
     * @param view
     * 
     */
    public SceneManagerImpl(final Stage stage, final View view) {
        this.stage = stage;
        this.sceneLoader = new SceneLoaderImpl(view);
    }

    /**
     * Load the main Scene.
     * 
     */
    @Override
    public void openInitialMenu() {
        this.openScene(SceneStyle.INITIALMENU);
    }

    /**
     *Load the tutorial Scene.
     */
    @Override
    public void openTutorial() {
        this.openScene(SceneStyle.TUTORIAL);
    }

    /**
     * Load the main Scene.
     */
    @Override
    public void openDiseaseChoice() {
        this.openScene(SceneStyle.CHOOSEDISEASE);
    }

    @Override
    public void openDiseaseName() {
        this.openScene(SceneStyle.DISEASENAME);
    }

    @Override
    public void openMutationScene() {
        this.openScene(SceneStyle.MUTATION);
    }

    /**
     * Set the previous scene.
     */
    @Override
    public void openBackScene() {
        this.sceneLoader.loadBackScene(this.stage);
    }

    /**
     * Open the scene selected.
     * 
     * @param sceneStyle
     *                   the scene to be loaded.
     */
    private void openScene(final SceneStyle sceneStyle) {
        this.sceneLoader.loadScene(sceneStyle, this.stage);
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
