package globaloutbreak.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.model.api.Infodata;
import globaloutbreak.model.cure.Cure;
import globaloutbreak.model.dataanalyzer.DataAnalyzer;
import globaloutbreak.model.dataanalyzer.DeathNumberAnalyzer;
import globaloutbreak.model.cure.RegionCureStatus;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.events.CauseEvent;
import globaloutbreak.model.events.CauseEventsImpl;
import globaloutbreak.model.events.Event;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.message.MessageType;
import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.MeansState;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.voyage.Voyage;
import globaloutbreak.model.voyage.VoyageImpl;

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
    public void selectedRegion(final Region region) {
        this.selectedRegion = Optional.of(region);
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
    public void chosenDisease(final Disease disease, final String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chosenDisease'");
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
                case "areporti":
                    pot.put(k, this.disease.getAirInfectivity());
                default:
                    break;
            }

        });
        final Map<String, Map<Integer, Pair<Integer, Integer>>> voyages = this.voyage.extractMeans(this.getRegions(),
                pot);
        if (voyages.isEmpty()) {
            voyages.forEach((s, m) -> {
                m.forEach((i, p) -> {
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
        final int popTot = updateRegion.getPopTot();
        final int death = updateRegion.getNumDeath();
        if (death < popTot) {
            if (death + newdeath > popTot) {
                updateRegion.incDeathPeople(popTot - death);
                updateRegion.setCureStatus(RegionCureStatus.FINISHED);
                updateRegion.getTrasmissionMeans().stream()
                        .forEach(k -> k.setState(MeansState.CLOSE));
            } else if (death + newdeath < popTot) {
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
        return this.getRegions().stream()
                .filter(k -> k.equals(region))
                .toList().get(0);
    }

    @Override
    public void causeEvent() {
        final Optional<Pair<Region, Integer>> event = this.causeEvents.causeEvent(this.getRegions()
                .stream()
                .filter(k -> k.getCureStatus() != RegionCureStatus.FINISHED)
                .toList());
        if (event.isPresent()) {
            this.incDeathPeople(event.get().getY(), event.get().getX());
        }
    }

    @Override
    public List<Event> getEvents() {
        return new LinkedList<>(events);
    }

    @Override
    public void createCauseEvents() {
        this.causeEvents = new CauseEventsImpl(this.getEvents());
    }

    @Override
    public void setRegions(final List<Region> regions) {
        this.regions = new LinkedList<>(regions);
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
}
