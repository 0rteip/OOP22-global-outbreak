package diseasesreader;

import java.io.BufferedReader;
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
 * Class that reads Diseases names from a file.
 */
public class DiseasesReaderImpl implements DiseasesReader {

    /**
     * A diseases reader from file.
     */
    private final Logger logger = LoggerFactory.getLogger(DiseasesReaderImpl.class);

    private final String diseasesFilePath = "diseases/diseases.json";
    private List<String> diseases = new ArrayList<>();

    /**
     * Read the diseases names from file "diseases/diseases.json".
     */
    public DiseasesReaderImpl() {
        try {
            final ObjectMapper mapper = new ObjectMapper();

            final JsonNode node = mapper.readTree(new BufferedReader(new InputStreamReader(
                    ClassLoader.getSystemResourceAsStream(diseasesFilePath), StandardCharsets.UTF_8)));

            node.forEach(n -> {
                final Iterator<Entry<String, JsonNode>> iter = n.fields();
                while (iter.hasNext()) {
                    final Entry<String, JsonNode> value = iter.next();
                    diseases.add(value.getValue().asText());
                }

            });
        } catch (

        Exception e) {
            logger.warn("Unable to read {}: {}", diseasesFilePath, e);
        }
    }

    @Override
    public final List<String> getDiseases() {
        return List.copyOf(this.diseases);
    }
}
