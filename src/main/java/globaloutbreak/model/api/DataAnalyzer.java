package globaloutbreak.model.api;

/**
 * Data analyzer for values of type {@code T}.
 * 
 * @param <T> data type
 */
public interface DataAnalyzer<T> {

    /**
     * A values to be analyzed.
     * 
     * @param data
     *             analyzed values
     */
    void analyze(T data);
}
