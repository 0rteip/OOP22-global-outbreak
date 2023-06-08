package globaloutbreak;

import globaloutbreak.controller.Controller;
import globaloutbreak.controller.ControllerImpl;
import globaloutbreak.view.View;
import globaloutbreak.view.ViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * GlobalOutbreak main.
 */
public class Main extends Application {
    /**
     * @param args
     *             params.
     */
    public static void main(final String... args) {
        launch(args);
    }

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final View view = new ViewImpl(primaryStage);
        final Controller controller = new ControllerImpl(view);
        view.start(controller);
    }
}
