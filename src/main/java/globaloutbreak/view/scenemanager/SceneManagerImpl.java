package globaloutbreak.view.scenemanager;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import globaloutbreak.model.message.Message;
import globaloutbreak.view.utilities.SceneStyle;
import globaloutbreak.view.View;
import globaloutbreak.view.sceneloader.SceneLoader;
import globaloutbreak.view.sceneloader.SceneLoaderImpl;
import javafx.stage.Stage;

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
    // @formatter:off
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "We need to use the correct instance of The Stage the open the Scene in the correct way"
    )
    // @formatter:on
    public SceneManagerImpl(final Stage stage, final View view) {
        this.stage = stage;
        this.sceneLoader = new SceneLoaderImpl(view);
    }

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
     * Open the scene selected.
     * 
     * @param sceneStyle
     *                   the scene to be loaded.
     */
    private void openScene(final SceneStyle sceneStyle) {
        this.sceneLoader.loadScene(sceneStyle, this.stage);
    }

    @Override
    public SceneLoader getSceneLoader() {
        return this.sceneLoader;
    }

    @Override
    public void openSettings() {
        this.openScene(SceneStyle.SETTINGS);
    }

    @Override
    public void openMessage(final Message message) {
        this.sceneLoader.openDialog(this.stage, message);
    }
}
