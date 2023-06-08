package globaloutbreak.model.cure.prioriry;

import java.util.List;

/**
 * An interface for a reader of priority.
 */
public interface CurePriorityReader {

    /**
     * Returns the {@link Priority} list.
     * 
     * @return
     *         list of priority
     */
    List<Priority> getPriorities();
}
