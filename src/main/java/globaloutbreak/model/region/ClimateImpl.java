package globaloutbreak.model.region;

/**
 * Impl. of ClimatInt.
 */
public final class ClimateImpl implements Climate {
    private final float arid;
    private final float humid;
    private final float hot;
    private final float cold;

    /**
     * Constructor.
     * 
     * @param humid
     *              humid perc.
     * @param hot
     *              hot perc.
     */
    public ClimateImpl(final float humid, final float hot) {
        this.humid = humid;
        this.hot = hot;
        this.arid = 1 - humid;
        this.cold = 1 - hot;
    }

    /*
     * private void compute(final float humid, final float hot) {
     * this.arid = 1 - humid;
     * this.cold = 1 - hot;
     * }
     */

    @Override
    public float getArid() {
        return arid;
    }

    @Override
    public float getHumid() {
        return humid;
    }

    @Override
    public float getHot() {
        return hot;
    }

    @Override
    public float getCold() {
        return cold;
    }
}
