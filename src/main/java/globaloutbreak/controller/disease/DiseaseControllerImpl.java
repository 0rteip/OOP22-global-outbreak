package globaloutbreak.controller.disease;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.disease.DiseaseData;
import globaloutbreak.model.disease.DiseaseDataList;
import globaloutbreak.model.disease.DiseaseFactory;
import globaloutbreak.model.disease.DiseaseFactoryImpl;

/**
 * class that manage disease controller.
 */
public final class DiseaseControllerImpl implements DiseaseController {

    private final DiseaseDataList diseaseList = new DiseaseDataList();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public void readFile(final List<DiseaseData> diseaseList) {
        this.diseaseList.setDisease(diseaseList);
    }

    @Override
    public Disease createDisease(final String type) {
        final DiseaseData diseaseData = diseaseList.getDisease().stream().filter(e -> e.getType().equals(type))
                .findFirst()
                .orElse(null);
            final DiseaseFactory diseaseFactory = new DiseaseFactoryImpl();
            if (diseaseData != null) {
            return diseaseFactory.createDisease(diseaseData.getType(), this.getIfValid(diseaseData.getInfectivity(), "Infectivity"),
                    this.getIfValid(diseaseData.getLethality(), "Lethality"), this.getIfValid(diseaseData.getAirInfectivity(), "AirInfectivity"), this.getIfValid(diseaseData.getSeaInfectivity(), "seaInfectivity"),
                    this.getIfValid(diseaseData.getLandInfectivity(), "LandInfectivity"), this.getIfValid(diseaseData.getHeatInfectivity(), "heatInfectivity"),
                    this.getIfValid(diseaseData.getColdInfectivity(), "ColdInfectivity"),
                    this.getIfValidCure(diseaseData.getCureResistance()), this.getIfValid(diseaseData.getHumidityInfectivity(), "HumidityInfectivity"),
                    this.getIfValid(diseaseData.getAridityInfectivity(), "AridityInfectivity"), this.getIfValid(diseaseData.getPovertyInfectivity(), "PovertyInfectivity"));
        } else {
            this.logger.error("No disease dound of the type passed as argument ({})" + type);
            throw new NoSuchElementException("No disease found of the type: " + type);
        }
    }

    private float getIfValid(final float value, final String name) {
        if (value < 0 || value > 1) {
            logger.error("Error parameter update: The new value of {} is less than 0 or exceeds 1", name);
            return 0;
        }
        return value;
    }

    private float getIfValidCure(final float value){
       if (value < 1) {
            logger.error("Error parameter update: The new value of cureResistance less than 1");
            return 0;
        }
        return value;
    }
}
