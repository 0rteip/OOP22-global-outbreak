package globaloutbreak.controller.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import diseasereader.DiseaseReader;
import diseasereader.DiseaseReaderImpl;
import globaloutbreak.controller.api.Controller;
import globaloutbreak.controller.disease.DiseaseController;
import globaloutbreak.controller.disease.DiseaseControllerImpl;
import globaloutbreak.controller.region_controller.RegionController;
import globaloutbreak.controller.region_controller.RegionControllerInt;
import globaloutbreak.model.Model;
import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.api.Message;
import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.Region;
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
    private final RegionControllerInt regionController;
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
        this.regionController = new RegionController(this);
    }

    @Override
    public void startGame() {
        this.view.start(this);
    }

    @Override
    public void choosenDisease(final String type) {
        this.diseaseController.createDisease(type);
        //this.model.setDisease(this.diseaseController.getDisease());
    }

    @Override
    public void choosenDiseaseName(final String name) {
        this.diseaseController.setDiseaseName(name);
    }

    @Override
    public void selectedRegion(final int region) {
        this.regionController.selectRegion(region);
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


    @Override
    public Region getRegionByColor(int color) {
        return this.model.getRegions().stream().filter(k -> k.getColor() == color).findFirst().get();
    }

    @Override
    public void addRegion(int popTot, String name, Map<String, Pair<Integer, Optional<List<String>>>> reachableRegion,
            float urban, float poor, int color, int facilities, float hot, float humid) {
        this.model.addRegion(popTot, name, reachableRegion, urban, poor, color, facilities, hot, humid);
    }


}
