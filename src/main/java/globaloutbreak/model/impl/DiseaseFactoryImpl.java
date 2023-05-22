package globaloutbreak.model.impl;

import globaloutbreak.model.api.Disease;
import globaloutbreak.model.api.DiseaseFactory;

public class DiseaseFactoryImpl implements DiseaseFactory {

    private double infectivity;
    private double lethality;
    private double airTransmission;
    private double landTransmission;
    private double seaTransmission;
    private double hotTransmission;
    private double coldTransmission;
    private double cureResistance;

    public DiseaseFactoryImpl(final double infectivity, final double lethality, final double airTransmission,
            final double seaTransmission, final double landTransmission,
            final double hotTransmission, final double coldTransmission, final double cureResistance) {
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
            public double getInfectivity() {
                return infectivity;
            }

            @Override
            public double getLethality() {
                return lethality;
            }

            @Override
            public double getAirTransmission() {
                return airTransmission;
            }

            @Override
            public double getSeaTransmission() {
                return seaTransmission;
            }

            @Override
            public double getLandTransmission() {
                return landTransmission;
            }

            @Override
            public double getColdTransmission() {
                return coldTransmission;
            }

            @Override
            public double getHotTransmission() {
                return hotTransmission;
            }

            @Override
            public double getCureResistance() {
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
