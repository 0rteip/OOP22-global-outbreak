package globaloutbreak.model;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.cure.RegionCureStatus;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.events.CauseEventInt;
import globaloutbreak.model.events.CauseEvents;
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
    private final List<Region> regions = new LinkedList<>();
    private Optional<Region> selectedRegion = Optional.empty();
    private Voyage voyage;
    private final List<Event> events = new LinkedList<>();
    private CauseEventInt causeEvents;
    @Override
    public void addRegion(final int ppTot, final String name, 
            final Map<String, Pair<Integer, Optional<List<String>>>> reachableRegion, 
            final float urban, final float poor, final int color, 
            final int facilities, final float hot, final float humid) {
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
    public void chosenDisease(final Disease disease, final String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chosenDisease'");
    }

    @Override
    public Map<String, Map<Integer, Pair<Integer, Integer>>> extractVoyages() {
        //mettere un controllo
        return this.voyage.extractMeans(this.getRegions());
    }

    @Override
    public void incDeathPeople(final int newdeath, final Region region) {
        final Region updateRegion = getRegion(region);
        final int popTot = updateRegion.getPopTot();
        final int death = updateRegion.getNumDeath();
        if (death < popTot) {
            if (death + newdeath > popTot) {
                updateRegion.incDeathPeople(popTot - death);
                updateRegion.setCureStatus(RegionCureStatus.FINISHED);
            } else if (death + newdeath > popTot) {
                updateRegion.incDeathPeople(newdeath);
            } 
        }
    }

    @Override
    public void incOrDecInfectedPeople(final int newinfected, final Region region) {
        final Region updateRegion = getRegion(region);
        final int popTot = updateRegion.getPopTot();
        final int infected = updateRegion.getNumInfected();
        if (infected < popTot) {
            if (infected + newinfected >= popTot) {
                updateRegion.incDeathPeople(popTot - infected);
            } else {
                updateRegion.incDeathPeople(newinfected);
            }
        }
    }

    private Region getRegion(final Region region) {
        return this.getRegions().stream().filter(k -> k.equals(region)).toList().get(0);
    }

    @Override
    public Optional<Pair<Region, Integer>> causeEvent() {
        return this.causeEvents.causeEvent(this.getRegions());
    }

    @Override
    public List<Event> getEvents() {
        return new LinkedList<>(events);
    }

    @Override
    public void createCauseEvents() {
       this.causeEvents = new CauseEvents(this.getEvents());
    }
}
