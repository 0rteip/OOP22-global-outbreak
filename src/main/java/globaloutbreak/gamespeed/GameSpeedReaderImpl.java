package globaloutbreak.gamespeed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A game speed reader based on "speed.json".
 */
public final class GameSpeedReaderImpl implements GameSpeedReader {
    private static final String FILE_PATH = "gamespeed/speed.json";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final List<GameSpeed> speeds = new ArrayList<>();

    /**
     * Reads from file.
     */
    public GameSpeedReaderImpl() {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode node = mapper.readTree(new BufferedReader(new InputStreamReader(
                    ClassLoader.getSystemResourceAsStream(FILE_PATH), StandardCharsets.UTF_8)));

            node.forEach(n -> {
                final Iterator<Entry<String, JsonNode>> iter = n.fields();
                final GameSpeed.Builder gameSpeeBuilder = new GameSpeed.Builder();

                while (iter.hasNext()) {
                    final Entry<String, JsonNode> value = iter.next();
                    switch (value.getKey()) {
                        case "duration":
                            gameSpeeBuilder.setDuration(value.getValue().floatValue());
                            break;
                        case "name":
                            gameSpeeBuilder.setName(value.getValue().textValue());
                            break;
                        case "default":
                            gameSpeeBuilder.setDefault(value.getValue().booleanValue());
                            break;
                        default:
                            logger.warn("Value: {} not recognized", value);
                            break;
                    }
                }
                speeds.add(gameSpeeBuilder.build());
            });
        } catch (IOException e) {
            logger.warn("Unable to read {}:", speeds, e);
        }
    }

    @Override
    public List<GameSpeed> getGameSpeeds() {
        return List.copyOf(this.speeds);
    }

    @Override
    public GameSpeed getDefGameSpeed() {
        return this.speeds.stream()
                .filter(el -> el.isDefault())
                .findFirst()
                .orElseGet(() -> new GameSpeed.Builder().build());
    }
}
