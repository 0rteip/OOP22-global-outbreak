package globaloutbreak.view.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import globaloutbreak.view.api.SceneManager;
import globaloutbreak.view.api.MenuView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * Class that manage the scene changing.
 */
public final class SceneManagerImpl implements SceneManager {
    private static final int HEIGHTSCREEN = 360;
    private static final int WIDTHSCREEN = 800;

    private final Stack<Scene> history = new Stack<>();
    private final Map<String, Pair<FXMLLoader, Parent>> sceneMap;
    private final Stage stage;

    /**
     * Constructor that load menu scenes.
     * 
     * @throws IOException
     */
    public SceneManagerImpl(final Stage stage) throws IOException {
        this.stage = stage;
        this.stage.setResizable(false);
        this.stage.setTitle("Global Outbreak");

        sceneMap = new HashMap<>();
        sceneMap.put("menu iniziale", loadScene("layouts/MenuGui.fxml"));
        sceneMap.put("scelta nome", loadScene("layouts/DiseaseNameGui.fxml"));
        sceneMap.put("tutorial", loadScene("layouts/TutorialGui.fxml"));

        setManager();
    }

    /**
     * Load and set a scene.
     * 
     * @param name the name of fxml file
     */
    @Override
    public void loadScreen(final String name) {
        final Parent parent = sceneMap.get(name).getValue();
        final Scene scene = parent.getScene() != null ? parent.getScene()
                : new Scene(parent, WIDTHSCREEN, HEIGHTSCREEN);

        history.push(stage.getScene());
        stage.setScene(scene);

        if (!stage.isShowing()) {
            stage.show();
        }
    }

    /**
     * Set the previous scene.
     */
    @Override
    public void goBack() {
        if (!history.isEmpty()) {
            stage.setScene(history.pop());
        }
    }

    /**
     * Set SceneManager to scene controllers.
     */
    private void setManager() {
        this.sceneMap.values().stream()
                .map(Pair::getKey).forEach(loader -> ((MenuView) loader.getController()).setController(this));
    }

    private Pair<FXMLLoader, Parent> loadScene(final String fxmlPath) throws IOException {
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(fxmlPath));
        final Parent parent = loader.load();
        return new Pair<>(loader, parent);
    }
}
