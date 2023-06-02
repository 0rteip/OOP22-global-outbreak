package diseasesreader;

import java.util.List;

/**
 * Interface to read Diseases names from a file.
 */
public interface DiseasesReader {

    /**
     * Returns DiseasesNames.
     * 
     * @return
     *         List<String> list
     */
    List<String> getDiseases();

}
