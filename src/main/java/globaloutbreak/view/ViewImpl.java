package globaloutbreak.view;

import java.util.ArrayList;
import java.util.List;

import globaloutbreak.controller.api.Controller;
import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.api.Message;
import globaloutbreak.model.api.Voyage;
import globaloutbreak.model.disease.DiseaseData;
import globaloutbreak.view.scenemanager.SceneManager;
import globaloutbreak.view.scenemanager.SceneManagerImpl;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import settings.WindowSettings;
import settings.WindowSettingsImpl;

/**
 * Class ViewImpl.
 */
public final class ViewImpl implements View {

    private final WindowSettings settings = new WindowSettingsImpl();
    private final SceneManager manager;
    private final List<Button> diseasesButtons = new ArrayList<>();
    private List<String> mutations = new ArrayList<>();
    private Controller controller;
    private String desc;
    private Boolean active;
    private int cost;

    /**
     * @param stage
     */
    public ViewImpl(final Stage stage) {
        this.manager = new SceneManagerImpl(stage, this);
    }

    @Override
    public void start(final Controller controller) {
        this.controller = controller;
        this.manager.openInitialMenu();
    }

    @Override
    public void displayInfo(final Infodata info) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayInfo'");
    }

    @Override
    public void displayMessage(final Message message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayMessage'");
    }

    @Override
    public void displayVoyage(final Voyage voyage) {
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

    @Override
    public List<Button> getDiseasesButtons() {
        return List.copyOf(diseasesButtons);
    }

    @Override
    public List<String> getMutations() {
        return List.copyOf(mutations);
    }

    @Override
    public String  getDescription() {
        return desc;
    }

     @Override
    public String getCost() {
        return String.valueOf(cost);
    }
 
    @Override
    public void setMutationsName(final List<String> mutationsNames) {
       this.mutations = mutationsNames;
    }

    @Override
    public void setMutationsDesc(final String desc, final Boolean activate, final int cost) {
       this.desc = desc;
       this.active = activate;
       this.cost = cost;
    }

    @Override
    public boolean checkactivate() {
        return active;
    }

    @Override
    public void setDiseasesData(final List<DiseaseData> diseasesNames) {
        diseasesNames.stream().forEach(disease -> diseasesButtons.add(new Button(disease.getType())));
    }

    @Override
    public void choosenDisease(final String type) {
        this.getController().choosenDisease(type);
    }

    @Override
    public void choosenNameDisease(final String name) {
        this.getController().choosenDiseaseName(name);
    }

}
