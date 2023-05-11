package globaloutbreak;

import globaloutbreak.controller.api.MenuController;
import globaloutbreak.controller.impl.MenuControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;

// import globaloutbreak.controller.api.Controller;

/**
 * Application.
 */

public class GlobalOutbreakApp extends Application {

    /**
     * 
     */
    @Override
    public final void start(Stage primaryStage) throws Exception {
        final MenuController menuController = new MenuControllerImpl(primaryStage);
        menuController.loadScreen("layouts/MenuGui.fxml");
        primaryStage.setTitle("Global Outbreak");
        primaryStage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
