package globaloutbreak.model;

import java.util.List;
import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.cure.Cure;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.infodata.Infodata;
import globaloutbreak.model.region.Region;


/**
 * Implementation of model.
 */
public final class ModelImpl implements Model {

    private Cure cure;

    // /**
    //  * Creates a Model.
    //  */
    // public ModelImpl() {
    // }

    @Override
    public void chosenDisease(final Disease disease, final String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chosenDisease'");
    }

    @Override
    public void setCure(final Cure cure) {
        this.cure = cure;
    }

    @Override
    public void selectedRegion(final Region region) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectedRegion'");
    }

    @Override
    public boolean selectedMutation(final Mutation mutation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectedMutation'");
    }

    @Override
    public List<Region> getRegions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRegions'");
    }

    @Override
    public List<Disease> getDiseases() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDiseases'");
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

    @Override
    public boolean isGameOver() {
        cure.isCompleted();
        return false;
    }

}
