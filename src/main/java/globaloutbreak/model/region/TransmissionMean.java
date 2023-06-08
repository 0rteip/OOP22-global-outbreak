package globaloutbreak.model.region;

import java.util.List;
import java.util.Optional;

/**
 * The class for mean.
 */
public interface TransmissionMean {

    /**
     * 
     * @return
     *          reachable States
     */
    Optional<List<String>> getReachableStates();

    /**
     * 
     * @return
     *          the means's state 
     */
    MeansState getState();

    /**
     * This method change the mean's state.
     * @param state 
     *               new mean's state 
     *
     */
    void setState(MeansState state);

    /**
     * 
     * @return
     *          the mean's type
     */
    String getType();
}
