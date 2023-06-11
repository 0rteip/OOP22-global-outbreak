package globaloutbreak.view.scenemanager;

import java.util.concurrent.CountDownLatch;

import globaloutbreak.model.message.Message;
import globaloutbreak.view.sceneloader.SceneLoader;

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
    void openWorldGraphScene();

    /**
     * 
     */
    void openCureGraphScene();

    /**
     * 
     */
    void openMap();

    /**
     * 
     * @return
     *         the {@link SceneLoader}.
     */
    SceneLoader getSceneLoader();

    /**
     * Open a message.
     * 
     * @param message
     *                message
     * @param latch
     *                CountDownLatch
     */
    void openMessage(Message message, CountDownLatch latch);

    /**
     * Open Mutation scene
     */
    void openMutationScene();
}