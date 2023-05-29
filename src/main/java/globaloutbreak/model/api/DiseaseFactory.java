package globaloutbreak.model.api;

/**
 * Interface that models the disease creator.
 */
public interface DiseaseFactory {

    /**
     * @param name
     * 
     * @return
     *         The Virus
     */
    Disease createDisease(String name);

}
