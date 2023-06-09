package globaloutbreak.controller.impl;

import java.io.IOException;
import diseasereader.DiseaseReader;
import diseasereader.DiseaseReaderImpl;
import globaloutbreak.controller.api.Controller;
import globaloutbreak.controller.disease.DiseaseController;
import globaloutbreak.controller.disease.DiseaseControllerImpl;
import globaloutbreak.controller.region_controller.RegionControllerImpl;
import globaloutbreak.controller.region_controller.RegionController;
import globaloutbreak.model.Model;
import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.api.Message;
import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.voyage.Voyage;
import globaloutbreak.view.View;
import javafx.application.Platform;

/**
 * Controller implementation.
 */
public final class ControllerImpl implements Controller {

    private final View view;
    private final DiseaseController diseaseController;
    private final Model model;
    private final RegionController regionController;
    /**
     * Create a controller.
     * 
     * @param view
     *             view of application
     * @param model
     *              monel of application
     * @throws IOException
     */
    public ControllerImpl(final View view, final Model model) throws IOException {
        this.view = view;
        this.model = model;
        this.diseaseController = new DiseaseControllerImpl();
        this.regionController = new RegionControllerImpl();
    }

    @Override
    public void startGame() {
        this.view.start(this);
    }

    @Override
    public void choosenDisease(final String type) {
        this.diseaseController.createDisease(type);
        //this.model.setDisease(this.diseaseController.getDisease());
        this.model.setRegions(this.regionController.getRegions());
    }

    @Override
    public void choosenDiseaseName(final String name) {
        this.diseaseController.setDiseaseName(name);

    }

    @Override
    public void selectedRegion(final int region) {
        this.model.selectedRegion(this.regionController.findRegionByColor(region));
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
