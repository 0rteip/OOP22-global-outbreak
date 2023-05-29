package globaloutbreak.controller.api;

import java.io.IOException;

import globaloutbreak.model.api.Disease;

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
     * 
     * @return
     *         Disease
     */
    Disease createDisease(String name);
}
