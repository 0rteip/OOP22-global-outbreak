package globaloutbreak.view.scenecontroller;

import globaloutbreak.gamespeed.GameSpeed;

/**
 * Define an interface for scene that need an update.
 */
public interface SceneUpdater {

    /**
     * Update scene speed.
     * 
     * @param gameSpeed
     *                  speed
     */
    void updateScene(GameSpeed gameSpeed);
}
