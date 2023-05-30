package globaloutbreak.view.scenemanager;

import java.io.IOException;

import java.util.Stack;

import globaloutbreak.view.View;
import globaloutbreak.view.scenecontroller.AbstractSceneController;
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
     * @param viewImpl
     * 
     * @throws IOException
     */
    public SceneManagerImpl(View view) {
        this.sceneLoader = new SceneLoaderImpl(view);
    }

    /**
     * Load and set a scene.
     * 
     * @param name the name of fxml file
     */
    @Override
    public void openInitialMenu(final Stage stage) {
        this.openScene(SceneStyle.INITIALMENU, stage);
    }

    @Override
    public void openTutorial(final Stage stage) {
        this.openScene(SceneStyle.TUTORIAL, stage);
    }

    @Override
    public void openDiseaseChoice(final Stage stage) {
        // this.openScene(SceneStyle.CHOOSEDISEASE, stage);
    }

    @Override
    public void openDiseaseName(final Stage stage) {
        this.openScene(SceneStyle.DISEASENAME, stage);
    }

    /**
     * Set the previous scene.
     */
    @Override
    public void openBackScene(final Stage stage) {
        this.sceneLoader.loadBackScene(stage);
    }

    /**
     * Set SceneManager to scene controllers.
     */
    private void openScene(final SceneStyle sceneStyle, final Stage stage) {
        this.sceneLoader.loadScene(sceneStyle, stage);
    }
}
