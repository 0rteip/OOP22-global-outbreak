package globaloutbreak.controller.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import globaloutbreak.controller.api.DiseaseController;
import globaloutbreak.model.api.Disease;
import globaloutbreak.model.api.DiseaseFactory;
import globaloutbreak.model.impl.DiseaseFactoryImpl;

/**
 * class that manage disease controller.
 */
public class DiseaseControllerImpl implements DiseaseController {
    private final Map<String, Float> diseaseData = new HashMap<>();

    /**
     * class to read diasea paramters file.
     */
    @Override
    public void readFile(final String diseaseFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode arrayNode = mapper.readTree(ClassLoader.getSystemResourceAsStream(diseaseFilePath));

        if (arrayNode.isArray()) {
            for (JsonNode node : arrayNode) {
                diseaseData.put(node.get("name").asText(), node.get("value").floatValue());
            }
        }
        System.out.println(diseaseData);
    }

    /**
     * Class that creates disease.
     */
    @Override
    public Disease createDisease(String name) {
        DiseaseFactory diseaseFactory = new DiseaseFactoryImpl(diseaseData.get("infectivity"),
                diseaseData.get("lethality"), diseaseData.get("airTransmission"), diseaseData.get("seaTransmission"),
                diseaseData.get("landTransmission"), diseaseData.get("heatTransmission"),
                diseaseData.get("coldTransmission"), diseaseData.get("cureResistance"),
                diseaseData.get("aridityResistance"), diseaseData.get("humidityResistance"),
                diseaseData.get("povertyTransmission"));

        return diseaseFactory.createDisease(name);
    }
}
