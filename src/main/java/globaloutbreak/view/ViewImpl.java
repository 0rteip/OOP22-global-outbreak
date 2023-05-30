package globaloutbreak.view;

import globaloutbreak.controller.api.Controller;
import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.api.Message;
import globaloutbreak.model.api.Voyage;
import globaloutbreak.view.scenemanager.SceneManager;
import globaloutbreak.view.scenemanager.SceneManagerImpl;
import javafx.stage.Stage;
import settings.WindowSettings;
import settings.WindowSettingsImpl;

public class ViewImpl implements View {

    final WindowSettings settings = new WindowSettingsImpl();
    final SceneManager manager;
    Controller controller;

    public ViewImpl() {
        this.manager = new SceneManagerImpl(this);
    }

    @Override
    public void start(final Controller controller, final Stage stage) {
        this.controller = controller;
        this.manager.openInitialMenu(stage);
    }

    @Override
    public void displayInfo(Infodata info) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayInfo'");
    }

    @Override
    public void displayMessage(Message message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayMessage'");
    }

    @Override
    public void displayVoyage(Voyage voyage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayVoyage'");
    }

    @Override
    public Controller getController() {
        return this.controller;
    }

    @Override
    public WindowSettings getWindowSettings() {
        return this.settings;
    }

    @Override
    public SceneManager getSceneManager() {
        return this.manager;
    }

}
