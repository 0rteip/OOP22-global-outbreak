package globaloutbreak.controller.impl;

import diseasesreader.DiseasesReader;
import diseasesreader.DiseasesReaderImpl;
import globaloutbreak.controller.api.Controller;
import globaloutbreak.model.api.Disease;
import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.api.Message;
import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.api.Region;
import globaloutbreak.model.api.Voyage;
import globaloutbreak.view.View;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Controller implementation.
 */
public class ControllerImpl implements Controller {

    private final View view;

    /**
     * Create a controller.
     * 
     * @param view
     *             view of application
     */
    public ControllerImpl(final View view) {
        this.view = view;
    }

    /**
     * Start game.
     */
    @Override
    public void startGame(final Stage stage) {
        this.view.start(this, stage);
    }

    /**
     * Create disease.
     */
    @Override
    public void choosenDisease(final Disease disease, final String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choosenDisease'");
    }

    @Override
    public void selectedRegion(final Region region) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectedRegion'");
    }

    @Override
    public void selectedMutation(final Mutation mutation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectedMutation'");
    }

    @Override
    public void updateInfo(final Infodata info) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateInfo'");
    }

    @Override
    public void displayMessage(final Message message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayMessage'");
    }

    @Override
    public void startVoyage(final Voyage voyage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startVoyage'");
    }

    @Override
    public void quit() {
        Platform.exit();
    }

    /**
     * Read diseases names from file.
     */
    @Override
    public void readDiseasesNames() {
        DiseasesReader reader = new DiseasesReaderImpl();

        this.view.setDiseasesNames(reader.getDiseases());
    }
}
