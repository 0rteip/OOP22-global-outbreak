package globaloutbreak.model.region;


import java.util.List;
import java.util.Optional;
/**
 * Implement. of TransmissionMean.
 */
public final class TransmissionMeansImpl implements TransmissionMean {
    private final Optional<List<String>> reachableStates;
    private final String type;
    private MeansState state;
    /**
     * Constructor.
     * 
     * @param reachableStates
     *                         reachable States
     * @param type
     *              the type of means
     */
    public TransmissionMeansImpl(final Optional<List<String>> reachableStates, final String type) {
        this.reachableStates = reachableStates;
        this.state = MeansState.OPEN;
        this.type = type;
    }

    @Override
    public Optional<List<String>> getReachableStates() {
        return reachableStates;
    }

    @Override
    public MeansState getState() {
        return state;
    }

    @Override
    public void setState(final MeansState state) {
        this.state = state;
    }

    @Override
    public String getType() {
        return type;
    }

}
