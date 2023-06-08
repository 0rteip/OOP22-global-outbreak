package globaloutbreak.mutationreader;

import java.util.List;

import globaloutbreak.model.mutation.Mutation;

/**
 * Interface that manage the mutation file reader.
 */

public interface MutationReader {

    /**
     * 
     * @return
     *         the list of DiseaseData
     */
    List<Mutation> getMutation();
}
