package globaloutbreak.view.api;

import java.io.IOException;

/**
 * Interface that manage the javaFX scenes.
 */
public interface SceneManager {

    /**
     * Load a new scene.
     * 
     * @param fxmlPath
     * @throws IOException
     */
    void loadScreen(String fxmlPath) throws IOException;

    /**
     * Go to prec Screen.
     */
    void goBack();
}
