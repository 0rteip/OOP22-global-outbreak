package globaloutbreak.model.region;
/**
 * Impl. of ClimatInt.
 */
public final class Climate implements ClimateInt {
    private float arid;
    private float humid;
    private float hot;
    private float cold;

    /**
     * Constructor.
     * 
     * @param humid
     *              humid perc.
     * @param hot
     *              hot perc.
     */
    public Climate(final float humid, final float hot) {
        this.humid = humid;
        this.hot = hot;
        this.compute(arid, hot);
    }

    private void compute(final float humid, final float hot) {
        this.arid = 1 - humid;
        this.cold = 1 - hot;
    }

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
