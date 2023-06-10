package globaloutbreak.view.sceneloader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.scene.layout.Region;
import globaloutbreak.model.message.Message;
import globaloutbreak.view.utilities.SceneStyle;
import globaloutbreak.view.View;
import globaloutbreak.view.messagedialog.MessageDialog;
import globaloutbreak.view.scenecontroller.SceneController;
import globaloutbreak.view.scenecontroller.SceneInitializer;
import globaloutbreak.view.scenecontroller.SceneUpdater;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Implementation of {@link SceneLoader}.
 */
public final class SceneLoaderImpl implements SceneLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private FXMLLoader loader;
    private final View view;
    private final Map<SceneStyle, Scene> sceneLoaded = new HashMap<>();

    /**
     * Create a SceneLoader with an associated view.
     * 
     * @param view
     *             view
     */
    // @formatter:off
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "We need to use the correct instance of the View to let the SceneController to access to the View"
    )
    // @formatter:on
    public SceneLoaderImpl(final View view) {
        this.view = view;
    }

    @Override
    public void loadScene(final SceneStyle sceneStyle, final Stage stage) {
        try {
            final Region root;
            final Scene scene;

            // If the scene is already in cache, set the cached scene.
            if (this.sceneLoaded.containsKey(sceneStyle)) {
                scene = this.sceneLoaded.get(sceneStyle);
                this.loader = (FXMLLoader) scene.getUserData();
            } else {
                // If the scene isn't in the cache, then create a new one, with the current
                // root.
                this.loader = new FXMLLoader();
                this.loader.setLocation(ClassLoader.getSystemResource(sceneStyle.getFxmlFile()));
                root = this.loader.load();
                scene = new Scene(root, (double) this.view.getWindowSettings().getDefWindowWidth(),
                        (double) this.view.getWindowSettings().getDefWindowHeight());
                scene.setUserData(this.loader);
                // scene.getStylesheets().add(ClassLoader.getSystemResource(sceneStyle.getCssFile()).toExternalForm());
                this.sceneLoaded.put(sceneStyle, scene);
            }

            // Set the current size in settings.
            stage.widthProperty().addListener((obs, oldVal, newVal) -> {
                this.view.getWindowSettings().setWidth(newVal.intValue());
            });
            stage.heightProperty().addListener((obs, oldVal, newVal) -> {
                this.view.getWindowSettings().setHeight(newVal.intValue());
            });

            stage.setTitle(sceneStyle.getTitle());
            stage.setResizable(true);
            stage.setScene(scene);
            stage.setMinWidth(this.view.getWindowSettings().getDefWindowWidth());
            stage.setMinHeight(this.view.getWindowSettings().getDefWindowHeight());
            stage.setHeight(this.view.getWindowSettings().getDefWindowHeight());
            stage.setWidth(this.view.getWindowSettings().getDefWindowWidth());

            final SceneController controller = (SceneController) loader.getController();
            this.initializeScene(controller, sceneStyle);

            if (!stage.isShowing()) {
                stage.show();
            }
        } catch (IOException e) {
            logger.warn("Error while loading {}", sceneStyle.getFxmlFile(), e);
        }
    }

    private void initializeScene(final SceneController controller, final SceneStyle sceneStyle) {
        controller.setSceneManager(this.view.getSceneManager());
        controller.setView(this.view);
        switch (sceneStyle) {
            case CHOOSEDISEASE,  MUTATION,  SETTINGS, WORLDGRAPH, CUREGRAPH:
                final SceneInitializer sceneInitController = (SceneInitializer) controller;
                sceneInitController.initializeScene();
                break;
            case MAP:
                final SceneInitializer sceneInitController2 = (SceneInitializer) controller;
                sceneInitController2.initializeScene();
                final SceneUpdater sceneUpdater = (SceneUpdater) controller;
                sceneUpdater.updateScene(view.getGameSettings().getGameSpeeds().stream().min((e1, e2) -> Float.compare(e1.getDuration(), e2.getDuration())).get());
                break;
            default:
                break;
        }
    }

    @Override
    public void openDialog(final Stage stage, final Message message) {
        MessageDialog.showMessageDialog(stage, message, this.view);
    }
}
