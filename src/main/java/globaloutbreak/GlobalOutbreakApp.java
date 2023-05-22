package globaloutbreak;

import globaloutbreak.view.api.SceneManager;
import globaloutbreak.view.impl.SceneManagerImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Application.
 */

public class GlobalOutbreakApp extends Application {

    /**
     * Set controller and load scene.
     */
    @Override
    public final void start(final Stage primaryStage) throws Exception {

        final SceneManager sceneManager = new SceneManagerImpl();
        sceneManager.loadScreen("menu iniziale", primaryStage);

    }

    /**
     * Main.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
