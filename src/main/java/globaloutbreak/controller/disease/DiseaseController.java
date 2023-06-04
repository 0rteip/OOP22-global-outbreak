package globaloutbreak.controller.disease;

import java.io.IOException;

import globaloutbreak.model.disease.Disease;

/**
 * Manage Disease data.
 */
public interface DiseaseController {

    /**
     * 
     * read all Disease data from file.
     * 
     * @param diseaseFilePath
     * 
     * @throws IOException
     */
    void readFile(String diseaseFilePath) throws IOException;

    /**
     * @param name
     * @param type
     * 
     * @return
     *         Disease
     */
    Disease createDisease(String name, String type);
}
