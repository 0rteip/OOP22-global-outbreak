package globaloutbreak.view.sceneloader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.layout.Region;
import globaloutbreak.model.message.Message;
import globaloutbreak.view.View;
import globaloutbreak.view.messagedialog.MessageDialog;
import globaloutbreak.view.scenecontroller.SceneController;
import globaloutbreak.view.scenecontroller.SceneInitializer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.utilities.SceneStyle;

/**
 * Implementation of {@link SceneLoader}.
 */
public final class SceneLoaderImpl implements SceneLoader {

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

    private void initializeScene(final SceneController controller, final SceneStyle sceneStyle) {
        controller.setSceneManager(this.view.getSceneManager());
        controller.setView(this.view);
        switch (sceneStyle) {
            case CHOOSEDISEASE:
                final SceneInitializer sceneInitController = (SceneInitializer) controller;
                sceneInitController.initializeScene();
                break;
            default:
                break;
        }
    }

    @Override
    public void openDialog(Stage stage, Message message) {
        MessageDialog.showMessageDialog(stage, message, this.view);
    }
}