package globaloutbreak.controller.disease;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import diseasereader.DiseaseReader;
import diseasereader.DiseaseReaderImpl;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.disease.DiseaseData;
import globaloutbreak.model.disease.DiseaseDataList;
import globaloutbreak.model.disease.DiseaseFactory;
import globaloutbreak.model.disease.DiseaseFactoryImpl;

/**
 * class that manage disease controller.
 */
public class DiseaseControllerImpl implements DiseaseController {

    private final DiseaseDataList diseaseList = new DiseaseDataList();
    private final Logger logger = LoggerFactory.getLogger(DiseaseReaderImpl.class);

    /**
     * class to read diseases paramters file.
     */
    @Override
    public void readFile(final String diseaseFilePath) throws IOException {
        final DiseaseReader reader = new DiseaseReaderImpl();
        diseaseList.setDisease(reader.getDiseases());
    }

    /**
     * @return
     *         Disease
     */
    @Override
    public Disease createDisease(final String name, final String type) {
        final DiseaseData diseaseData = diseaseList.getDisease().stream().filter(e -> e.getType().equals(type))
                .findFirst()
                .orElse(null);

        if (diseaseData != null) {
            final DiseaseFactory diseaseFactory = new DiseaseFactoryImpl();

            return diseaseFactory.createDisease(name, diseaseData.getType(), diseaseData.getInfectivity(),
                    diseaseData.getLethality(), diseaseData.getAirInfectivity(), diseaseData.getSeaInfectivity(),
                    diseaseData.getLandInfectivity(), diseaseData.getHeatInfectivity(),
                    diseaseData.getColdInfectivity(),
                    diseaseData.getCureResistance(), diseaseData.getHumidityInfectivity(),
                    diseaseData.getAridityInfectivity(), diseaseData.getPovertyInfectivity());
        }

        this.logger.error("No disease dound of the type passed as argument ({})" + type);
        throw new NoSuchElementException("No disease found of the type: " + type);
    }

}
