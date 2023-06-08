package globaloutbreak.view.sceneloader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import globaloutbreak.model.message.Message;
import globaloutbreak.view.View;
import globaloutbreak.view.messagedialog.MessageDialog;
import globaloutbreak.view.scenecontroller.SceneController;
import globaloutbreak.view.scenecontroller.SceneInitializer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import view.utilities.SceneStyle;

/**
 * Implementation of {@link SceneLoader}.
 */
public final class SceneLoaderImpl2 implements SceneLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private FXMLLoader loader;
    private final View view;
    private final Map<SceneStyle, Scene> sceneLoaded = new HashMap<>();
    private Optional<SceneStyle> lastScene = Optional.empty();

    /**
     * Create a SceneLoader with an associated view.
     * 
     * @param view
     *             view
     */
    // @formatter:off
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "We need to use the correct instance of View"
    )
    // @formatter:on
    public SceneLoaderImpl2(final View view) {
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
                scene = new Scene(root, (double) this.view.getWindowSettings().getWindowWidth(),
                        (double) this.view.getWindowSettings().getWindowHeight());
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
            stage.setHeight(this.view.getWindowSettings().getWindowHeight());
            stage.setWidth(this.view.getWindowSettings().getWindowWidth());

            final SceneController controller = (SceneController) loader.getController();
            this.initializeScene(controller, sceneStyle);

            if (this.lastScene.isEmpty()) {
                this.lastScene = Optional.of(sceneStyle);
            }

            if (!stage.isShowing()) {
                stage.show();
            }
        } catch (IOException e) {
            logger.warn("Error while loading {}", sceneStyle.getFxmlFile(), e);
        }
    }

    @Override
    public void loadBackScene(final Stage stage) {
        this.lastScene.ifPresent(sS -> this.loadScene(sS, stage));
    }

    @Override
    public void openDialog(final Stage stage, final Message message) {
        MessageDialog.showMessageDialog(stage, message, this.view);
    }

    private void initializeScene(final SceneController controller, final SceneStyle sceneStyle) {
        controller.setSceneManager(this.view.getSceneManager());
        controller.setView(this.view);
        switch (sceneStyle) {
            case CHOOSEDISEASE, SETTINGS:
                final SceneInitializer settingsController = (SceneInitializer) controller;
                settingsController.initializeScene();
                break;
            default:
                break;
        }
    }
    // private void initializeScene(final SceneController controller, final
    // SceneStyle sceneStyle) {
    // controller.setSceneAdministrator(this.view.getSceneAdministrator());
    // controller.setView(this.view);
    // switch (sceneType) {
    // case SIMULATION:
    // final SimulationInitializer simulationController = (SimulationInitializer)
    // controller;
    // simulationController.initSimulationController();
    // break;
    // default:
    // break;
    // }
    // }
}
