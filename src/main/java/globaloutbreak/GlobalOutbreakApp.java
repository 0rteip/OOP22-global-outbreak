package globaloutbreak;

import globaloutbreak.controller.api.Controller;
import globaloutbreak.controller.impl.ControllerImpl;
import globaloutbreak.view.View;
import globaloutbreak.view.ViewImpl;
import globaloutbreak.view.scenemanager.SceneManager;
import globaloutbreak.view.scenemanager.SceneManagerImpl;
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

        // final SceneManager sceneManager = new SceneManagerImpl();
        // sceneManager.loadScreen("menu iniziale", primaryStage);
        final View view = new ViewImpl();
        final Controller controller = new ControllerImpl(view);
        controller.startGame(primaryStage);

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
