package globaloutbreak.view.scenemanager;

import globaloutbreak.model.message.Message;
import globaloutbreak.view.View;
import globaloutbreak.view.sceneloader.SceneLoader;
import globaloutbreak.view.sceneloader.SceneLoaderImpl2;
import javafx.stage.Stage;
import view.utilities.SceneStyle;

/**
 * SceneManager implementation.
 */
public final class SceneManagerImpl2 implements SceneManager {

    private final Stage stage;
    private final SceneLoader loader;

    /**
     * A scene Manager Builder.
     * 
     * @param stage
     *              stage
     * @param view
     *              view
     */
    public SceneManagerImpl2(final Stage stage, final View view) {
        this.stage = stage;
        this.loader = new SceneLoaderImpl2(view);
    }

    @Override
    public void openInitialMenu() {
        this.loadScene(SceneStyle.INITIALMENU);
    }

    @Override
    public void openTutorial() {
        this.loadScene(SceneStyle.INITIALMENU);

    }

    @Override
    public void openDiseaseChoice() {
        this.loadScene(SceneStyle.CHOOSEDISEASE);

    }

    @Override
    public void openDiseaseName() {
        this.loadScene(SceneStyle.DISEASENAME);
    }

    @Override
    public void openSettings() {
        this.loadScene(SceneStyle.SETTINGS);
    }

    @Override
    public void openBackScene() {
        this.loader.loadBackScene(this.stage);

    }

    @Override
    public SceneLoader getSceneLoader() {
        return this.loader;
    }

    @Override
    public void openMessage(final Message message) {
        this.loader.openDialog(this.stage, message);
    }

    private void loadScene(final SceneStyle scene) {
        this.loader.loadScene(scene, stage);
    }
}
