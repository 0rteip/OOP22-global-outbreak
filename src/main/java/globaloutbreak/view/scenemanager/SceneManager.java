package globaloutbreak.view.scenemanager;

import java.util.concurrent.CountDownLatch;

import globaloutbreak.model.message.Message;

/**
 * Interface that manage the javaFX scenes.
 */
public interface SceneManager {

    /**
     * Open menu Scene.
     */
    void openInitialMenu();

    /**
     * Open tutorial Scene.
     */
    void openTutorial();

    /**
     * Open diseaseChoice Scene.
     */
    void openDiseaseChoice();

    /**
     * Open diseaseName Scene.
     */
    void openDiseaseName();

    /**
     * Open Settings Scene.
     */
    void openSettings();

    /**
     * Open last Scene.
     */
    void openWorldGraph();

    /**
     * Open Cure Graph Scene.
     */
    void openCureGraph();

    /**
     * Open Mutation Scene.
     */
    void openMutation();

    /**
     * Open map Scene.
     */
    void openMap();

    /**
     * Open a Message.
     * 
     * @param message
     *                message
     * @param latch
     *                CountDownLatch
     */
    void openMessage(Message message, CountDownLatch latch);
}
