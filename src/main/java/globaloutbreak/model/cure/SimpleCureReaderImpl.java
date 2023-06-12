package globaloutbreak.model.cure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import globaloutbreak.model.cure.prioriry.CurePriorityReaderImpl;
import globaloutbreak.model.cure.prioriry.Priority;
import globaloutbreak.model.region.Region;

/**
 * A simple cure reader based on "cure.json".
 */
public final class SimpleCureReaderImpl implements SimpleCureReader {
    private static final String FILE_PATH = "cure/cure.json";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public SimpleCure getSimpleCure(final List<Region> regions) {
        final List<Priority> priorities = new CurePriorityReaderImpl().getPriorities();
        try {
            final SimpleCure.Builder cureBuilder = new SimpleCure.Builder(regions, priorities);

            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode node = mapper.readTree(new BufferedReader(new InputStreamReader(
                    ClassLoader.getSystemResourceAsStream(FILE_PATH), StandardCharsets.UTF_8)));

            final Iterator<Entry<String, JsonNode>> it = node.fields();

            while (it.hasNext()) {
                final Entry<String, JsonNode> value = it.next();
                switch (value.getKey()) {
                    case "dailyBudget":
                        cureBuilder.setDailyBudget(value.getValue().floatValue());
                        break;
                    case "numberOfMajorContributors":
                        cureBuilder.setNumberOfMajorContributors(value.getValue().intValue());
                        break;
                    case "researchersEfficiency":
                        cureBuilder.setResearchersEfficiency(value.getValue().floatValue());
                        break;
                    case "necessaryBudget":
                        cureBuilder.setNecessaryBudget(value.getValue().floatValue());
                        break;
                    case "researchBudget":
                        cureBuilder.setResearchBudget(value.getValue().floatValue());
                        break;
                    case "currentPriority":
                        cureBuilder.setCurrentPriority(value.getValue().intValue());
                        break;
                    case "daysBeforeStartResearch":
                        cureBuilder.setDaysBeforeStartResearch(value.getValue().intValue());
                        break;
                    case "rilevantProgress":
                        final JsonNode progs = value.getValue();
                        if (progs.isArray()) {
                            final Set<Integer> ints = new HashSet<>();
                            for (final JsonNode numberNode : progs) {
                                ints.add(numberNode.asInt());
                            }
                            cureBuilder.setRilevantProgress(ints);
                        }
                        break;
                    default:
                        logger.warn("Value '{}' not recognized", value);
                        break;
                }
            }
            return cureBuilder.build();
        } catch (IOException e) {
            logger.warn("Unable to read {}:", FILE_PATH, e);
            return new SimpleCure.Builder(regions, priorities).build();
        }
    }

}
