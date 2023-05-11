package globaloutbreak.controller.impl;

import java.io.IOException;
import java.util.Stack;

import globaloutbreak.controller.api.MenuController;
import globaloutbreak.view.api.MenuView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuControllerImpl implements MenuController {
    private final Stack<Scene> history = new Stack<>();
    private final Stage stage;

    public MenuControllerImpl(Stage stage) {
        this.stage = stage;
    }

    /*
     * Load and set a scene
     * 
     * @param resource the name of fxml file
     */
    public void loadScreen(String resource) {
        try {
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(resource));
            Parent parent = loader.load();
            final MenuView menuView = loader.getController();
            menuView.setController(this);
            Scene scene = new Scene(parent, 800, 360);
            history.push(stage.getScene());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Set the previous scene
     */
    public void goBack() {
        if (!history.isEmpty()) {
            stage.setScene(history.pop());
        }
    }
}
