package globaloutbreak.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.model.cure.Cure;
import globaloutbreak.model.dataanalyzer.DataAnalyzer;
import globaloutbreak.model.dataanalyzer.DeathNumberAnalyzer;
import globaloutbreak.model.cure.RegionCureStatus;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.events.CauseEvent;
import globaloutbreak.model.events.Event;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.message.MessageType;
import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.model.infodata.InfoDataImpl;
import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.voyage.Voyage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Impl of Model interface.
 */
public final class ModelImpl implements Model {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Disease disease;
    private List<Region> regions = new LinkedList<>();
    private Optional<Region> selectedRegion = Optional.empty();
    private Voyage voyage;
    private Optional<Cure> cure = Optional.empty();
    private List<Event> events = new LinkedList<>();
    private final DataAnalyzer<Integer> deathAnalyzer;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Optional<Message> newsMessage = Optional.empty();
    private CauseEvent causeEvents;
    private InfoData infoData;

    /**
     * Creates a model.
     */
    public ModelImpl() {
        this.deathAnalyzer = new DeathNumberAnalyzer((key, value) -> {
            final Message message = new Message() {
                @Override
                public MessageType getType() {
                    return MessageType.NEWS;
                }

                @Override
                public String toString() {
                    return disease.getName() + " killed more than: " + key + "\nMore than " + value + " people.";
                }
            };
            pcs.firePropertyChange(MessageType.NEWS.getTitle(), newsMessage, message);
            newsMessage = Optional.of(message);
        });
    }

    @Override
    public void addNewsListener(final PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    @Override
    public List<Region> getRegions() {
        return new LinkedList<>(this.regions);
    }

    @Override
    public void selectedRegion(final Optional<Region> region) {
        this.selectedRegion = region;
    }

    @Override
    public boolean selectedMutation(final String mutation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectedMutation'");
    }

    @Override
    public void setDisease(final Disease disease) {
        this.disease = disease;
    }

    @Override
    public void setDiseaseName(final String name) {
        this.disease.setName(name);
    }

    @Override
    public boolean isDiseaseSet() {
        return Objects.isNull(this.disease);
    }

    @Override
    public Disease getDisease(){
        return this.disease;
    }

    @Override
    public InfoData getInfo() {
        this.infoData.updateTotalDeathsAndInfected(regions.stream()
                .filter(region -> region.getNumDeath() > 0)
                .map(region -> region.getNumDeath())
                .reduce(0, (m1, m2) -> m1 + m2),
                regions.stream()
                        .map(region -> region.getNumInfected())
                        .reduce(0, (i1, i2) -> i1 + i2));

        if (this.cure.isPresent()) {
            this.infoData.updateCureData(this.cure.get().getGlobalStatus());
        }

        return this.infoData;
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
    public Voyage getVoyage() {
        return this.voyage;
    }

    @Override
    public void extractVoyages() {
        final Map<String, Float> pot = new HashMap<>();
        voyage.getMeans().forEach(k -> {
            switch (k) {
                case "terra":
                    pot.put(k, this.disease.getLandInfectivity());
                    break;
                case "porti":
                    pot.put(k, this.disease.getSeaInfectivity());
                    break;
                case "areporti" : pot.put(k, this.disease.getAirInfectivity());
                    break;
                default : 
                    break;
            }

        });
        final Map<String, Map<Pair<Integer, Integer>, Integer>> voyages = this.voyage.extractMeans(this.getRegions(), pot);
        if (voyages.isEmpty()) {
            voyages.forEach((s, m) -> {
                m.forEach((p, i) -> {
                    final Optional<Region> r = getRegionByColor(p.getY());
                    if (r.isPresent()) {
                        this.incOrDecInfectedPeople(i.intValue(), r.get());
                    }
                });
            });
        }
    }

    private Optional<Region> getRegionByColor(final int color) {
        return this.getRegions().stream()
                .filter(k -> k.getColor() == color)
                .findFirst();
    }

    @Override
    public void incDeathPeople(final int newdeath, final Region region) {
        final Region updateRegion = getRegion(region);
        updateRegion.incDeathPeople(newdeath);
    }

    @Override
    public void incOrDecInfectedPeople(final int newinfected, final Region region) {
        final Region updateRegion = getRegion(region);
        updateRegion.incOrDecInfectedPeople(newinfected);
    }

    private Region getRegion(final Region region) {
        return this.getRegions().stream()
                .filter(k -> k.equals(region))
                .toList().get(0);
    }

    @Override
    public void causeEvent() {
        final Optional<Pair<String, Pair<Region, Integer>>> event = this.causeEvents.causeEvent(this.getRegions()
                .stream()
                .filter(k -> k.getCureStatus() != RegionCureStatus.FINISHED)
                .toList());
        if (event.isPresent()) {
            this.incDeathPeople(event.get().getY().getY(), event.get().getY().getX());
        }
    }

    @Override
    public void createCauseEvents() {
       
    }

    @Override
    public void setRegions(final List<Region> regions) {
        this.regions = new LinkedList<>(regions);
        this.infoData = new InfoDataImpl(this.regions.stream()
                .map(Region::getPopTot)
                .reduce(0, (e0, e1) -> e0 + e1));
    }

    @Override
    public void setEvents(final List<Event> events) {
        this.events = new LinkedList<>(events);
    }

    @Override
    public void setCure(final Cure cure) {
        this.cure = Optional.of(cure);
    }

    @Override
    public boolean isGameOver() {
        if (this.cure.isPresent()) {
            return this.cure.get().isCompleted();
        }
        logger.info("No Cure setted, closing game");
        return true;
    }

    @Override
    public void createVoyage(Map<String, org.apache.commons.lang3.tuple.Pair<Integer, Integer>> sizeAndNameOfMeans) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createVoyage'");
    }

    // private CureData emptyCureData() {
    // return new CureData() {

    // @Override
    // public int getProgress() {
    // return 0;
    // }

    // @Override
    // public int getRemainingDays() {
    // return -1;
    // }

    // @Override
    // public List<Region> getMajorContributors() {
    // return new ArrayList<>();
    // }

    // @Override
    // public Priority gePriority() {
    // CurePriority.Builder b = new CurePriority.Builder();
    // return b.build();
    // }

    // };
    // }
}
