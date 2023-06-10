package globaloutbreak.controller.voyage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.voyage.Voyage;
import globaloutbreak.model.voyage.VoyageImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implement. of VoyageController.
 */
public final class VoyageControllerImpl implements VoyageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Voyage createVoyage() {
        final ObjectMapper map = new ObjectMapper();
        final String path = "voyage/ConfigMeans.json";
        JsonNode node;
        final Map<String, Pair<Integer, Integer>> sizeAndNameOfMeans = new HashMap<>();
        try {
            node = map.readTree(new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path),
                    StandardCharsets.UTF_8)));
            node.forEach(k -> {
                final Iterator<Entry<String, JsonNode>> iterator = k.fields();
                int num = 0;
                String type = "";
                int pass = 0;
                while (iterator.hasNext()) {
                    final Entry<String, JsonNode> e = iterator.next();
                    switch (e.getKey()) {
                        case "type":
                            type = e.getValue().textValue();
                            break;
                        case "passengers":
                            pass = e.getValue().intValue();
                            break;
                        case "num":
                            num = e.getValue().intValue();
                            break;
                        default:
                            break;
                    }
                }
                sizeAndNameOfMeans.put(type, new Pair<>(num, pass));
            });
        } catch (IOException e) {
            logger.error("Failed creation of events", e);
        }
        return new VoyageImpl(sizeAndNameOfMeans);

    }
}
