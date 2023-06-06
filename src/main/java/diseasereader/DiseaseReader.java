package diseasereader;

import java.util.List;

import globaloutbreak.model.disease.DiseaseData;

/**
 * Interface that manage the diseases file reader.
 */
public interface DiseaseReader {

    /**
     * 
     * @return
     *         the list of DiseaseData
     */
    List<DiseaseData> getDiseases();
}
