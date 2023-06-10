package globaloutbreak.mutationreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map.Entry;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import globaloutbreak.model.mutation.MutationData;
import globaloutbreak.model.mutation.TypeMutation;

import org.slf4j.Logger;
/**
 * Class that reads Mutation file.
 */

public class MutationReaderImpl implements MutationReader {

    private static final String DISEASES_FILE_PATH = "mutation/Mutation.json";
    private String name;

    private int cost;

    private float increase;

    private TypeMutation type;

    private String description;

    private final MutationData mutationData;

    private final Logger logger = LoggerFactory.getLogger(MutationReaderImpl.class);

    /**
     * Constructor.
     * 
     * Read file and create DiseaseDate objects
     * @param mutationData the mutation data
     * 
     */

    public MutationReaderImpl(final MutationData mutationData) {
       this.mutationData = mutationData;
    }

    /**
     * get mutation.
     * 
     */

    @Override
    public void readMutation() {
         try {
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode node = mapper.readTree(new BufferedReader(new InputStreamReader(
                    ClassLoader.getSystemResourceAsStream(DISEASES_FILE_PATH), StandardCharsets.UTF_8)));

            node.forEach(n -> {
                final Iterator<Entry<String, JsonNode>> iter = n.fields();
                while (iter.hasNext()) {
                    final Entry<String, JsonNode> value = iter.next();

                    switch (value.getKey()) {
                        case "cost":
                            this.cost = value.getValue().intValue();
                            break;
                        case "name":
                            this.name = value.getValue().textValue();
                            break;
                        case "increase":
                            this.increase = value.getValue().floatValue();
                            break;
                        case "type":
                            this.type = TypeMutation.valueOf(value.getValue().asText());
                            value.getValue().textValue();
                            break;
                        case "description":
                            this.description = value.getValue().textValue();
                            break;
                        default:
                            logger.error("String not recognized in file {}", DISEASES_FILE_PATH);
                            break; 
                    }
                }
                mutationData.loadMutationFromJson(cost, name, increase, type, description);
            });
        } catch (IOException e) {
            logger.warn("Errore durante l'analisi o la mappatura del contenuto JSON nel file {}: {}",
                DISEASES_FILE_PATH, e);
        }
    }
}
