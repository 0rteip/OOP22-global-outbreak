package globaloutbreak.view.sceneloader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Stream;

import javafx.util.Pair;
import globaloutbreak.view.View;
import globaloutbreak.view.scenecontroller.SceneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.utilities.SceneStyle;

/**
 * Implementation of {@link SceneLoader}.
 */
public class SceneLoaderImpl implements SceneLoader {

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

    @Override
    public final void loadScene(final SceneStyle sceneStyle, final Stage stage) {

        this.history.push(stage.getScene());

        final Parent root = sceneMap.get(sceneStyle.getTitle()).getValue();
        final Scene scene = root.getScene() != null ? root.getScene()
                : new Scene(root, (double) this.view.getWindowSettings().getDefWindowWidth(),
                        (double) this.view.getWindowSettings().getDefWindowHeight());

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

        final SceneController controller = (SceneController) sceneMap.get(sceneStyle.getTitle()).getKey()
                .getController();
        controller.setSceneManager(this.view.getSceneManager());
        controller.setView(this.view);

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

    @Override
    public void loadBackScene(final Stage stage) {
        if (!history.isEmpty()) {
            stage.setScene(history.pop());
        }
    }
}
