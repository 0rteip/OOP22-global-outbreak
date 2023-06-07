package globaloutbreak.diseasereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import globaloutbreak.model.disease.DiseaseData;

/**
 * Class that reads Diseases file.
 */
public class DiseaseReaderImpl implements DiseaseReader {

    private static final String DISEASES_FILE_PATH = "diseases/DiseaseData.json";

    private final Logger logger = LoggerFactory.getLogger(DiseaseReaderImpl.class);
    private final List<DiseaseData> diseases = new ArrayList<>();

    /**
     * Constructor.
     * 
     * Read file and create DiseaseDate objects.
     */
    public DiseaseReaderImpl() {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode node = mapper.readTree(new BufferedReader(new InputStreamReader(
                    ClassLoader.getSystemResourceAsStream(DISEASES_FILE_PATH), StandardCharsets.UTF_8)));

            node.forEach(n -> {
                final DiseaseData diseaseData = new DiseaseData();
                final Iterator<Entry<String, JsonNode>> iter = n.fields();
                while (iter.hasNext()) {
                    final Entry<String, JsonNode> value = iter.next();

                    switch (value.getKey()) {
                        case "type":
                            diseaseData.setType(value.getValue().textValue());
                            break;
                        case "infectivity":
                            diseaseData.setInfectivity(value.getValue().floatValue());
                            break;
                        case "lethality":
                            diseaseData.setLethality(value.getValue().floatValue());
                            break;
                        case "airInfectivity":
                            diseaseData.setInfectivity(value.getValue().floatValue());
                            break;
                        case "landInfectivity":
                            diseaseData.setInfectivity(value.getValue().floatValue());
                            break;
                        case "seaInfectivity":
                            diseaseData.setInfectivity(value.getValue().floatValue());
                            break;
                        case "heatInfectivity":
                            diseaseData.setInfectivity(value.getValue().floatValue());
                            break;
                        case "coldInfectivity":
                            diseaseData.setInfectivity(value.getValue().floatValue());
                            break;
                        case "cureResistance":
                            diseaseData.setCureResistance(value.getValue().floatValue());
                            break;
                        case "humidityInfectivity":
                            diseaseData.setInfectivity(value.getValue().floatValue());
                            break;
                        case "aridityInfectivity":
                            diseaseData.setInfectivity(value.getValue().floatValue());
                            break;
                        case "povertyInfectivity":
                            diseaseData.setInfectivity(value.getValue().floatValue());
                            break;
                        default:
                            logger.error("String not recognized in file {}", DISEASES_FILE_PATH);
                            break;
                    }
                }
                diseases.add(diseaseData);
            });
        } catch (IOException e) {
            logger.warn("Errore durante l'analisi o la mappatura del contenuto JSON nel file {}: {}",
                    DISEASES_FILE_PATH,
                    e);
        }
    }

    /**
     * @return
     *         a copy of DiseaseData list.
     */
    @Override
    public List<DiseaseData> getDiseases() {
        return List.copyOf(diseases);
    }
}
