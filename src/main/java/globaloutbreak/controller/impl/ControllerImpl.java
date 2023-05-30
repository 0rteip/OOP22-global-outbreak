package globaloutbreak.controller.impl;

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
     * @param model
     *              model of application
     * @param view
     *              view of application
     */
    public ControllerImpl(final View view) {
        this.view = view;
    }

    @Override
    public void startGame(final Stage stage) {
        this.view.start(this, stage);
    }

    @Override
    public void choosenDisease(Disease disease, String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choosenDisease'");
    }

    @Override
    public void selectedRegion(Region region) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectedRegion'");
    }

    @Override
    public void selectedMutation(Mutation mutation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectedMutation'");
    }

    @Override
    public void updateInfo(Infodata info) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateInfo'");
    }

    @Override
    public void displayMessage(Message message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayMessage'");
    }

    @Override
    public void startVoyage(Voyage voyage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startVoyage'");
    }

    @Override
    public void quit() {
        Platform.exit();
    }

    // private class SimulationLoop extends Thread {
    // private static final int UPDATES_IN_A_DAY = 10;
    // private volatile boolean running;
    // private final DayCicle dayCicle = new DayCicleImpl(UPDATES_IN_A_DAY);
    // private final Object mutex = new Object();

    // SimulationLoop() {
    // this.setDaemon(true);
    // this.running = true;
    // }

    // @Override
    // public void run() {
    // while (this.running && !ControllerImpl.this.model.isSimulationOver()) {
    // final long startTime = System.currentTimeMillis();
    // this.update();
    // this.render();
    // final int elapsedTime = (int) (System.currentTimeMillis() - startTime);
    // this.waitForNextFrame(ControllerImpl.this.gameSpeed.getDuration(),
    // elapsedTime);
    // synchronized (this.mutex) {
    // try {
    // if (!this.running) {
    // this.mutex.wait();
    // }
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // }
    // }

    // private void update() {
    // ControllerImpl.this.update(dayCicle);
    // if (ControllerImpl.this.model.isSimulationOver()) {
    // ControllerImpl.this.view.setSimulationOver();
    // }
    // }

    // private void render() {
    // ControllerImpl.this.view.render(ControllerImpl.this.model.getFoods(),
    // ControllerImpl.this.model.getOrganisms());
    // }

    // private void waitForNextFrame(final DayDuration dayDuration, final int
    // elapsed) {
    // final int timeUntilNextLoop = dayDuration.getDuration() * 1000 /
    // UPDATES_IN_A_DAY - elapsed;
    // // the sleep time cannot be < 0, this would cause an exception
    // if (timeUntilNextLoop > 0) {
    // try {
    // Thread.sleep(timeUntilNextLoop);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }this.view.start(this);
    // }
    // }

    // public void startStop() {
    // this.running = !this.running;
    // synchronized (this.mutex) {
    // if (this.running) {
    // this.mutex.notifyAll();
    // }
    // }
    // }
    // }
}
