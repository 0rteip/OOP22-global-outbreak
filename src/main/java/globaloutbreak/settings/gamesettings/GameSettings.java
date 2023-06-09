package globaloutbreak.settings.gamesettings;

import globaloutbreak.gamespeed.GameSpeed;

/**
 * Interface for GameSettings.
 */
public interface GameSettings extends GameSettingsGetter, Cloneable {

    /**
     * Set the current GameSpeed.
     * 
     * @param gameSpeed
     *                  game speed to set
     */
    void setGameSpeed(GameSpeed gameSpeed);

    /**
     * Clone the settings.
     * 
     * @return
     *         GameSettings
     */
    GameSettings clone();
}
