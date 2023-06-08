package globaloutbreak;

import globaloutbreak.controller.Controller;
import globaloutbreak.controller.ControllerImpl;
import globaloutbreak.model.Model;
import globaloutbreak.model.ModelImpl;
import globaloutbreak.view.View;
import globaloutbreak.view.ViewImpl;
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

        final View view = new ViewImpl(primaryStage);
        final Model model = new ModelImpl();
        final Controller controller = new ControllerImpl(view, model);
        controller.startGame();
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
