package globaloutbreak.gamespeed;

import java.util.List;

/**
 * An interface for a reader of game speed.
 */
public interface GameSpeedReader {

    /**
     * Returns The list of game Speeds.
     * 
     * @return
     *         list of game speeds
     */
    List<GameSpeed> getGameSpeeds();

    /**
     * @return
     *         the defaut {@link GameSpeed}
     */
    GameSpeed getDefGameSpeed();
}
