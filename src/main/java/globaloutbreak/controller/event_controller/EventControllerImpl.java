package globaloutbreak.controller.event_controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import globaloutbreak.model.Model;
import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.Region;

/**
 * 
 */
public final class EventControllerImpl implements EventController {
    private final Model model;
    /**
     * Constructor.
     * @param model
     *              model
     * @throws IOException
     */
    public EventControllerImpl(final Model model) throws IOException {
        this.model = model;
        final String path = "region/ConfigMeans.json";
        final ObjectMapper map = new ObjectMapper();
        final JsonNode node = map.readTree(new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path), 
                StandardCharsets.UTF_8)));
        node.forEach(k -> {
            final Iterator<Entry<String, JsonNode>> iterator = k.fields();
            float morti = 0;
            String name = "";
            float prob = 0;
            while (iterator.hasNext()) {
                final Entry<String, JsonNode> e = iterator.next();
                switch (e.getKey()) {
                    case "name" : name = e.getValue().textValue();
                        break;
                    case "prob" : prob = e.getValue().floatValue();
                        break;
                    case "morti" : morti = e.getValue().floatValue();
                        break;
                    default :
                        break;
                }
            }
            this.model.addEvent(morti, name, prob);
        });

        this.model.createCauseEvents();
    }
    @Override
    public Optional<Pair<Region, Integer>> causeEvent() {
        return this.model.causeEvent();
    }

}
