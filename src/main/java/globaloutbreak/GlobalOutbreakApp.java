package globaloutbreak;

import java.io.IOException;

import globaloutbreak.controller.api.DiseaseController;
import globaloutbreak.controller.impl.DiseaseControllerImpl;
import globaloutbreak.model.api.Disease;

// import globaloutbreak.controller.api.Controller;

/**
 * Applpication.
 */
public final class GlobalOutbreakApp {

    private GlobalOutbreakApp() {
    }

    /**
     * App entry point.
     * 
     * @param args
     *             ignored
     */

    public static void main(final String[] args) {
        final DiseaseController controller = new DiseaseControllerImpl();
        try {
            controller.readFile("diseases/DiseaseData.json");
            Disease disease = controller.createDisease("name");
        } catch (IOException e) {
            System.out.println("Not working");
        }
        // System.out.println("fine");
    }

}
