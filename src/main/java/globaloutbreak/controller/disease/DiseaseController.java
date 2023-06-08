package globaloutbreak.controller.disease;

import java.util.List;

import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.disease.DiseaseData;

/**
 * Manage Disease data.
 */
public interface DiseaseController {

    /**
     *  read all Disease data from file.
     * 
     * @param diseaseList
     * a list of all Disease data.
     */
    void readFile(List<DiseaseData> diseaseList);

    /**
     * Create a new Disease.
     * 
     * @param type
     *         Disease type.
     * 
     * @return
     *         Disease
     */
    Disease createDisease(String type);
}
