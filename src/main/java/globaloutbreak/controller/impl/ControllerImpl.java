package globaloutbreak.controller.impl;

import diseasereader.DiseaseReader;
import diseasereader.DiseaseReaderImpl;
import globaloutbreak.controller.api.Controller;
import globaloutbreak.controller.disease.DiseaseController;
import globaloutbreak.controller.disease.DiseaseControllerImpl;
import globaloutbreak.model.Model;
import globaloutbreak.controller.mutation.MutationControllerImpl;
import globaloutbreak.model.api.Infodata;
//import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.api.Message;
//import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.api.Region;
import globaloutbreak.model.api.Voyage;
import globaloutbreak.model.mutation.Mutation;
import globaloutbreak.view.View;
import javafx.application.Platform;

/**
 * Controller implementation.
 */
public final class ControllerImpl implements Controller {

    private final View view;
    private final DiseaseController diseaseController;
    private final Model model;
    private final MutationControllerImpl mutationcControllerImpl;

    /**
     * Create a controller.
     * 
     * @param view
     *             view of application
     * @param model
     *             view of application
     */
    public ControllerImpl(final View view, final Model model) {
        this.view = view;
        this.model = model;
        this.diseaseController = new DiseaseControllerImpl();
        this.mutationcControllerImpl = new MutationControllerImpl(view, diseaseController);
    }

    @Override
    public void startGame() {
        this.view.start(this);
    }

    @Override
    public void choosenDisease(final String type) {
        this.diseaseController.createDisease(type);
        this.model.setDisease(this.diseaseController.getDisease());
    }

    @Override
    public void choosenDiseaseName(final String name) {
        this.diseaseController.setDiseaseName(name);
    }

    @Override
    public void selectedRegion(final Region region) {
    }

   // @Override
    //public void selectedMutation(final Mutation mutation) {
   // }

  //  @Override
    //public void updateInfo(final Infodata info) {
   // }

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
    public void displayMutation() {
        this.mutationcControllerImpl.displayMutationsName();
    }
    @Override
    public void update(final String name) {
        this.mutationcControllerImpl.update(name);
    }
    @Override
    public void displayMutationDesc(final String name) {
        this.mutationcControllerImpl.displayMutationsDesc(name);
    }

    @Override
    public void updateInfo(final Infodata info) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateInfo'");
    }

    @Override
    public void selectedMutation(final Mutation mutation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectedMutation'");
    }
}
