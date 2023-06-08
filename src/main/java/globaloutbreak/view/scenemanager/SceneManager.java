package globaloutbreak.view.scenemanager;

import globaloutbreak.view.sceneloader.SceneLoader;

/**
 * Interface that manage the javaFX scenes.
 */
public interface SceneManager {

    /**
     * 
     */
    void openInitialMenu();

    /**
     * 
     */
    void openTutorial();

    /**
     * 
     */
    void openDiseaseChoice();

    /**
     * 
     */
    void openDiseaseName();

    /**
     * 
     */
    void openBackScene();

    /**
     * 
     */
    void openMutationScene();
    
    /**
     * 
     * @return
     *         the {@link SceneLoader}.
     */
    SceneLoader getSceneLoader();
}
