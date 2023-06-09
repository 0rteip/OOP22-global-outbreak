package globaloutbreak.controller.voyage_cotroller;

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

import globaloutbreak.model.Model;
import globaloutbreak.model.pair.Pair;

/**
 * Implement. of VoyageController.
 */
public final class VoyageControllerImpl implements VoyageController {
    private final Model model;
    /**
     * Construct.
     * @param model
     * @throws IOException
     */
    public VoyageControllerImpl(final Model model) throws IOException {
        this.model = model;
        final ObjectMapper map = new ObjectMapper();
        final String path = "region/ConfigMeans.json";
        final JsonNode node = map.readTree(new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path), 
                StandardCharsets.UTF_8)));
        final Map<String, Pair<Integer, Integer>> sizeAndNameOfMeans = new HashMap<>();
        node.forEach(k -> {
            final Iterator<Entry<String, JsonNode>> iterator = k.fields();
            while (iterator.hasNext()) {
                int num = 0;
                String type = "";
                int pass = 0;
                final Entry<String, JsonNode> e = iterator.next();
                switch (e.getKey()) {
                    case "type" : type = e.getValue().textValue();
                        break;
                    case "passengers" : pass = e.getValue().intValue();
                        break;
                    case "num" : num = e.getValue().intValue();
                        break;
                    default :
                        break;
                }
                sizeAndNameOfMeans.put(type, new Pair<>(num, pass));
            }
        });
        this.model.createVoyage(sizeAndNameOfMeans);
    }
    @Override
    public Map<String, Map<Integer, Pair<Integer, Integer>>> extractVoyages() {
        return this.model.extractVoyages();
    }
}
