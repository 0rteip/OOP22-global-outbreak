package globaloutbreak.model.impl;


public class ClimateImpl {
    private float arid;
    private float humid;
    private float hot;
    private float cold;

    public ClimateImpl(final float arid, final float hot) {
        this.arid = arid;
        this.hot = hot;
        this.compute(arid, hot);
    }

    private void compute(final float arid, final float hot) {
        this.humid = 1 - arid;
        this.cold = 1 - hot;
    }
    
    public float getArid() {
        return arid;
    }

    public float getHumid() {
        return humid;
    }

    public float getHot() {
        return hot;
    }

    public float getCold() {
        return cold;
    }

    
}
