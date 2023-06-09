package globaloutbreak.model;

import java.util.List;

import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.api.Region;
/**
 * class model impl.
 */

public final class ModelImpl implements Model { 
    private Disease disease;

    @Override
    public void setDisease(final Disease disease) {
        this.disease = disease;
    }

    @Override
    public void selectedRegion(final Region region) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectedRegion'");
    }

    @Override
    public boolean selectedMutation(final String mutation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectedMutation'");
    }

    @Override
    public List<Region> getRegions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRegions'");
    }

    @Override
    public Disease getDisease() {
        return this.disease;
    }

    @Override
    public Infodata getInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
    }

    @Override
    public List<Integer> getGlobalData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGlobalData'");
    }
}
