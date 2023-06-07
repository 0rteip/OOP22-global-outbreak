package globaloutbreak.view.scenemanager;

import globaloutbreak.model.message.Message;
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
        this.openScene(SceneStyle.CHOOSEDISEASE, stage);
    }

    @Override
    public void openDiseaseName(final Stage stage) {
        this.openScene(SceneStyle.DISEASENAME, stage);
    }

    @Override
    public void openBackScene(final Stage stage) {
        this.sceneLoader.loadBackScene(stage);
    }

    private void openScene(final SceneStyle sceneStyle, final Stage stage) {
        this.sceneLoader.loadScene(sceneStyle, stage);
    }

    @Override
    public SceneLoader getSceneLoader() {
        return this.sceneLoader;
    }

    @Override
    public void openMessage(final Message message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'openMessage'");
    }
}
