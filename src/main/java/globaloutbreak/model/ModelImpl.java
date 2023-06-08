package globaloutbreak.model;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.cure.Cure;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.events.Event;
import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.region.RegionImpl;
import globaloutbreak.model.voyage.Voyage;
import globaloutbreak.model.voyage.VoyageImpl;
import java.util.LinkedList;
/**
 * Impl of Model interface.
 */
public final class ModelImpl implements Model {
    private final List<Region> regions= new LinkedList<>();
    private Optional<Region> selectedRegion = Optional.empty();
    private Voyage voyage;
    private Cure cure;
    private final List<Event> events  = new LinkedList<>();

    @Override
    public void addRegion(final Integer ppTot, final String name, Map<String,Pair<Integer, Optional<List<String>>>> reachableRegion,  final float urban, final float poor, 
        final Integer color, final Integer facilities, final float hot, final float humid) {
        this.regions.add(new RegionImpl(ppTot, name, reachableRegion, urban, poor, color, facilities, hot, humid));
    }

    @Override
    public List<Region> getRegions() {
        return new LinkedList<>(this.regions);
    }


    @Override
    public void selectedRegion(final Region region) {
        this.selectedRegion = Optional.of(region);
    }

    @Override
    public boolean selectedMutation(final String mutation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectedMutation'");
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
    public Optional<Region> getSelectedRegion() {
        return this.selectedRegion;
    }

    @Override
    public void createVoyage(final Map<String, Pair<Integer, Integer>> sizeAndNameOfMeans) {
        this.voyage = new VoyageImpl(sizeAndNameOfMeans);
    }   

    @Override
    public Voyage getVoyage() {
        return this.voyage;
    }

    @Override
    public void addEvent(final float morti, final String name, final float prob) {
            events.add(new Event(name, prob, morti));
    }

    @Override
    public void chosenDisease(globaloutbreak.model.disease.Disease disease, String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chosenDisease'");
    }

    @Override
    public void setCure(final Cure cure) {
        this.cure = cure;
    }

    @Override
    public boolean isGameOver() {
        return this.cure.isCompleted();
    }
}
