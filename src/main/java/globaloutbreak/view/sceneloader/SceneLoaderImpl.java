package globaloutbreak.view.sceneloader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.util.Pair;
import globaloutbreak.view.View;
import globaloutbreak.view.scenecontroller.SceneController;
import globaloutbreak.view.scenecontroller.SettingsInitializer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.utilities.SceneStyle;

/**
 * Implementation of {@link SceneLoader}.
 */
public class SceneLoaderImpl implements SceneLoader {

    private final Logger logger = LoggerFactory.getLogger(SceneLoaderImpl.class);
    private final Map<String, Pair<FXMLLoader, Parent>> sceneMap = new HashMap<>();
    private final Stack<Scene> history = new Stack<>();
    private final View view;

    /**
     * Create a SceneLoader with an associated view.
     * 
     * @param view
     *             view
     */
    public SceneLoaderImpl(final View view) {
        this.view = view;
        this.loadFiles();
    }

    /**
     * Load a scene into Stage.
     */
    @Override
    public final void loadScene(final SceneStyle sceneStyle, final Stage stage) {

        this.history.push(stage.getScene());
        final Parent root = sceneMap.get(sceneStyle.getTitle()).getValue();
        final Scene scene = root.getScene() != null ? root.getScene()
                : new Scene(root, (double) this.view.getWindowSettings().getDefWindowWidth(),
                        (double) this.view.getWindowSettings().getDefWindowHeight());

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

        final SceneController controller = (SceneController) sceneMap.get(sceneStyle.getTitle()).getKey()
                .getController();
        controller.setSceneManager(this.view.getSceneManager());
        controller.setView(this.view);

        initializeScene(controller, sceneStyle);

        if (!stage.isShowing()) {
            stage.show();
        }
    }

    private void loadFiles() {
        Stream.of(SceneStyle.values())
                .forEach(scene -> {
                    try {
                        sceneMap.put(scene.getTitle(), this.loadScenes(scene.getFxmlFile()));
                    } catch (IOException e) {
                        logger.warn("Failed to load the scene: {}", scene);
                    }
                });
    }

    /**
     * Load all scenes and return Pair<FXMLLoader, Parent>.
     * 
     * @param fxmlPath
     * @return a new Pair<FXMLLoader, Parent> representing the loaded scene.
     * @throws IOException
     */
    private Pair<FXMLLoader, Parent> loadScenes(final String fxmlPath) throws IOException {
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(fxmlPath));
        final Parent parent = loader.load();
        return new Pair<>(loader, parent);
    }

    /**
     * Load the prec Scene.
     */
    @Override
    public void loadBackScene(final Stage stage) {
        System.out.println("Back");
        if (!history.isEmpty()) {
            stage.setScene(history.pop());
        }
    }

    /**
     * return
     * {@link SceneController}.
     */
    @Override
    public SceneController getController(final SceneStyle name) {
        return this.sceneMap.get(name.getTitle()).getKey().getController();
    }

    /**
     * Initialize scene settings.
     * 
     * @param controller
     *                   the {@link SceneController}
     * @param sceneStyle
     *                   the current {@link SceneStyle}
     */
    private void initializeScene(final SceneController controller, final SceneStyle sceneStyle) {
        switch (sceneStyle) {
            case CHOOSEDISEASE:
                final SettingsInitializer settingsController = (SettingsInitializer) controller;
                settingsController.initializeSettings();
                break;
            default:
                break;
        }
    }
}
