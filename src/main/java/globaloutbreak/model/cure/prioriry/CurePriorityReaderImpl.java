package globaloutbreak.model.cure.prioriry;

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
 * A Cure Priority reader based on "priorities.json".
 */
public final class CurePriorityReaderImpl implements CurePriorityReader {

    private static final String FILE_PATH = "cure/priorities.json";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Priority> getPriorities() {
        final List<Priority> priorities = new ArrayList<>();

        try {
            final CurePriority.Builder priorityBuilder = new CurePriority.Builder();
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode node = mapper.readTree(new BufferedReader(new InputStreamReader(
                    ClassLoader.getSystemResourceAsStream(FILE_PATH), StandardCharsets.UTF_8)));

            node.forEach(n -> {
                final Iterator<Entry<String, JsonNode>> iter = n.fields();

                while (iter.hasNext()) {
                    final Entry<String, JsonNode> value = iter.next();
                    switch (value.getKey()) {
                        case "priority":
                            priorityBuilder.setPriority(value.getValue().asInt());
                            break;
                        case "description":
                            priorityBuilder.setDescription(value.getValue().textValue());
                            break;
                        case "resourcesPercentage":
                            priorityBuilder.setResourcesPercentage(value.getValue().floatValue());
                            break;
                        case "detectionRate":
                            priorityBuilder.setDetectionRate(value.getValue().floatValue());
                            break;
                        default:
                            logger.warn("Value '{}' not recognized", value);
                            break;
                    }
                }
                priorities.add(priorityBuilder.build());
            });
        } catch (IOException e) {
            logger.warn("Unable to read {}:", FILE_PATH, e);
            priorities.add(new CurePriority.Builder().build());
        }
        return priorities;
    }

}
