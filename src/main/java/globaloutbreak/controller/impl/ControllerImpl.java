package globaloutbreak.controller.impl;

import diseasereader.DiseaseReader;
import diseasereader.DiseaseReaderImpl;
import globaloutbreak.controller.api.Controller;
import globaloutbreak.controller.disease.DiseaseController;
import globaloutbreak.controller.disease.DiseaseControllerImpl;
import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.api.Message;
import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.api.Region;
import globaloutbreak.model.api.Voyage;
import globaloutbreak.view.View;
import javafx.application.Platform;

/**
 * Controller implementation.
 */
public final class ControllerImpl implements Controller {

    private final View view;
    private final DiseaseController diseaseController;

    /**
     * Create a controller.
     * 
     * @param view
     *             view of application
     */
    public ControllerImpl(final View view) {
        this.view = view;
        this.diseaseController = new DiseaseControllerImpl();
    }

    @Override
    public void startGame() {
        this.view.start(this);
    }

    @Override
    public void choosenDisease(final String type) {
        this.diseaseController.createDisease(type);
    }

    @Override
    public void choosenDiseaseName(final String name) {
        this.diseaseController.setDiseaseName(name);
    }

    @Override
    public void selectedRegion(final Region region) {
    }

    @Override
    public void selectedMutation(final Mutation mutation) {    
    }

    @Override
    public void updateInfo(final Infodata info) {    
    }

    @Override
    public void displayMessage(final Message message) {     
    }

    @Override
    public void startVoyage(final Voyage voyage) {     
    }

    @Override
    public void quit() {
        Platform.exit();
    }

    @Override
    public void readDiseasesNames() {
        final DiseaseReader reader = new DiseaseReaderImpl();
        this.view.setDiseasesData(reader.getDiseases());
        this.diseaseController.readFile(reader.getDiseases());
    }
}
