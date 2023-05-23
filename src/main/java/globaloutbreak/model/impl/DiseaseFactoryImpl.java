package globaloutbreak.model.impl;

import globaloutbreak.model.api.Disease;
import globaloutbreak.model.api.DiseaseFactory;

public class DiseaseFactoryImpl implements DiseaseFactory {

    private float infectivity;
    private float lethality;
    private float airTransmission;
    private float landTransmission;
    private float seaTransmission;
    private float hotTransmission;
    private float coldTransmission;
    private float cureResistance;

    public DiseaseFactoryImpl(final float infectivity, final float lethality, final float airTransmission,
            final float seaTransmission, final float landTransmission,
            final float hotTransmission, final float coldTransmission, final float cureResistance) {
        this.infectivity = infectivity;
        this.lethality = lethality;
        this.airTransmission = airTransmission;
        this.seaTransmission = seaTransmission;
        this.landTransmission = landTransmission;
        this.hotTransmission = hotTransmission;
        this.coldTransmission = coldTransmission;
        this.cureResistance = cureResistance;
    }

    @Override
    public Disease createDisease() {
        return new Disease() {

            @Override
            public float getInfectivity() {
                return infectivity;
            }

            @Override
            public float getLethality() {
                return lethality;
            }

            @Override
            public float getAirTransmission() {
                return airTransmission;
            }

            @Override
            public float getSeaTransmission() {
                return seaTransmission;
            }

            @Override
            public float getLandTransmission() {
                return landTransmission;
            }

            @Override
            public float getColdTransmission() {
                return coldTransmission;
            }

            @Override
            public float getHotTransmission() {
                return hotTransmission;
            }

            @Override
            public float getCureResistance() {
                return cureResistance;
            }

            @Override
            public void setInfectivity() {
                infectivity += 0.05;
            }

            @Override
            public void setLethality() {
                lethality += 0.05;
            }

            @Override
            public void setAirTransmission() {
                airTransmission += 0.05;
            }

            @Override
            public void setSeaTransmission() {
                seaTransmission += 0.05;
            }

            @Override
            public void setLandTransmission() {
                landTransmission += 0.05;
            }

            @Override
            public void setHotTransmission() {
                hotTransmission += 0.05;
            }

            @Override
            public void setColdTransmission() {
                coldTransmission += 0.05;
            }

            @Override
            public void setCureResistance() {
                cureResistance += 0.05;
            }

            @Override
            public void infect() {

            }

            @Override
            public void kill() {

            }
        };
    }
}
