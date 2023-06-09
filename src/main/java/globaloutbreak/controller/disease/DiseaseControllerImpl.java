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
    private Disease disease;

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
            if (diseaseData != null && this.checkIfValid(diseaseData.getInfectivity(), "Infectivity") 
            && this.checkIfValid(diseaseData.getLethality(), "Lethality")
            && this.checkIfValid(diseaseData.getAirInfectivity(), "AirInfectivity")
            && this.checkIfValid(diseaseData.getSeaInfectivity(), "SeaInfectivity")
            && this.checkIfValid(diseaseData.getLandInfectivity(), "LandInfectivity")
            && this.checkIfValid(diseaseData.getHeatInfectivity(), "HeatInfectivity")
            && this.checkIfValid(diseaseData.getColdInfectivity(), "ColdInfectivity")
            && this.checkIfValid(diseaseData.getHumidityInfectivity(), "HumidityInfectivity")
            && this.checkIfValid(diseaseData.getAridityInfectivity(), "AridityInfectivity")
            && this.checkIfValid(diseaseData.getPovertyInfectivity(), "PovertyInfectivity")
            && this.checkIfValid(diseaseData.getCureResistance(), "CureResistance")) {
            return diseaseFactory.createDisease(diseaseData.getType(), diseaseData.getInfectivity(),
                    diseaseData.getLethality(), diseaseData.getAirInfectivity(), diseaseData.getSeaInfectivity(),
                    diseaseData.getLandInfectivity(), diseaseData.getHeatInfectivity(),
                    diseaseData.getColdInfectivity(),
                    diseaseData.getCureResistance(), diseaseData.getHumidityInfectivity(),
                    diseaseData.getAridityInfectivity(), diseaseData.getPovertyInfectivity());
        } else {
            this.logger.error("No disease dound of the type passed as argument ({})" + type);
            throw new NoSuchElementException("No disease found of the type: " + type);
        }
    }

    private boolean checkIfValid(final float value, final String name) {
        if (value < 0 || value > 1) {
            logger.error("Error parameter update: The new value of {} is less than 0 or exceeds 1", name);
            return false;
        }
        return true;
    }

}
