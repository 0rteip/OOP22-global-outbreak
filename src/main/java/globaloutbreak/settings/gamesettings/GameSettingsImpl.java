package globaloutbreak.settings.gamesettings;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.gamespeed.GameSpeedReader;
import globaloutbreak.gamespeed.GameSpeedReaderImpl;

/**
 * A GameSettings implementation.
 */
public final class GameSettingsImpl implements GameSettings {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final List<GameSpeed> gameSpeeds;
    private GameSpeed gameSpeed;

    /**
     * Creates a GameSettings implementation.
     */
    public GameSettingsImpl() {
        final GameSpeedReader gsr = new GameSpeedReaderImpl();
        this.gameSpeeds = gsr.getGameSpeeds();
        this.gameSpeed = gsr.getDefGameSpeed();
    }

    @Override
    public List<GameSpeed> getGameSpeeds() {
        return List.copyOf(this.gameSpeeds);
    }

    @Override
    public GameSpeed getGameSpeed() {
        return this.gameSpeed;
    }

    @Override
    public void setGameSpeed(final GameSpeed gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    @Override
    public GameSettingsImpl clone() {
        try {
            return (GameSettingsImpl) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.warn("Clone not supported for gameSettings", e);
            return this;
        }
    }
}
