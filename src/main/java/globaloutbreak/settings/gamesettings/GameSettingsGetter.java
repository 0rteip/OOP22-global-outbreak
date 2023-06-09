package globaloutbreak.settings.gamesettings;

import java.util.List;

import globaloutbreak.gamespeed.GameSpeed;

/**
 * Interface for gameSettings, only getter.
 */
public interface GameSettingsGetter {

    /**
     * Returns the {@link GameSpeed}s avalible for the game.
     * 
     * @return
     *         list of GameSpeed
     */
    List<GameSpeed> getGameSpeeds();

    /**
     * Returns the current {@link GameSpeed}.
     * 
     * @return
     *         GameSpeed
     */
    GameSpeed getGameSpeed();
}
