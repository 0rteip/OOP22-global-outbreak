package globaloutbreak.model.disease;

import java.util.List;
import java.util.ArrayList;

/**
 * Class to save all Diseases data.
 */
public class DiseaseDataList {
    private List<DiseaseData> disease = new ArrayList<>();

    /**
     * 
     * @return
     *         List of DiseaseData
     */
    public List<DiseaseData> getDisease() {
        return new ArrayList<>(this.disease);
    }

    /**
     * save all DiseaseData here.
     * 
     * @param disease
     *                List of DiseaseData
     */
    public void setDisease(final List<DiseaseData> disease) {
        this.disease = new ArrayList<>(disease);
    }
}
