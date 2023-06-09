package globaloutbreak.controller.event_controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import globaloutbreak.model.events.Event;
import globaloutbreak.model.events.EventImpl;

/**
 * 
 */
public final class EventControllerImpl implements EventController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public List<Event> createEvents() {
        final List<Event> events = new LinkedList<>();
        final String path = "region/ConfigMeans.json";
        final ObjectMapper map = new ObjectMapper();
        JsonNode node;
        try {
            node = map.readTree(new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path), 
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
                events.add(new EventImpl(name, prob, morti));
            });
        } catch (IOException e) {
            logger.error("Failed creation of events", e);
        }
        return new LinkedList<>(events);
    }

}
