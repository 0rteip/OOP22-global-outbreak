package globaloutbreak.model.api;

import java.util.Map;

/**
 * Data comparator for Datas stored in a map.
 * 
 * @param <S> Map data key
 * @param <T> Map data value
 */
public interface DataComparator<S, T> {

    /**
     * Provide new data to the comparator.
     * 
     * @param data
     *             data
     */
    void update(T data);

    /**
     * Describe the action to perform.
     * 
     * @param entry
     *              data
     */
    void action(Map.Entry<S, T> entry);
}
