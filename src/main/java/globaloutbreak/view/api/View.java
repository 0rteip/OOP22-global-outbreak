package globaloutbreak.view.api;

import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.api.Message;
import globaloutbreak.model.voyage.Voyage;

/**
 * interface View.
 */
public interface View {

    /**
     * Start the view.
     */
    void start();

    /**
     * Update visual info.
     * 
     * @param info
     *             to update
     */
    void displayInfo(Infodata info);

    /**
     * Display the message notification.
     * 
     * @param message
     *                to display
     */
    void displayMessage(Message message);

    /**
     * Display the Voyage.
     * 
     * @param voyage
     *          voyage to display
     */
    void displayVoyage(Voyage voyage);
}
