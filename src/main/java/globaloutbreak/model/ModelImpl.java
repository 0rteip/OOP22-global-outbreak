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
import globaloutbreak.model.events.CauseEventsImpl;
import globaloutbreak.model.events.Event;
import globaloutbreak.model.events.ExtractedEvent;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.message.MessageType;
import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.model.infodata.InfoDataImpl;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.voyage.Voyage;
import globaloutbreak.model.voyage.Voyages;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Impl of Model interface.
 */

public class ModelImpl implements Model {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Disease disease;
    private List<Region> regions = new LinkedList<>();
    private Optional<Region> selectedRegion = Optional.empty();
    private Voyages voyageC;
    private Optional<Cure> cure = Optional.empty();
    private final DataAnalyzer<Integer> deathAnalyzer;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Optional<Message> message = Optional.empty();
    private CauseEvent causeEvents;
    private InfoData infoData;
    private final static int INITIAL_INC = 1;
    /**
     * Creates a model.
     */
    public ModelImpl() {
        this.deathAnalyzer = new DeathNumberAnalyzer((key, value) -> {
            final Message msg = new Message() {
                @Override
                public MessageType getType() {
                    return MessageType.NEWS;
                }

                @Override
                public String toString() {
                    return disease.getName() + " killed more than: " + key + "\nMore than " + value + " people.";
                }
            };
            pcs.firePropertyChange(MessageType.NEWS.getTitle(), message, msg);
            message = Optional.of(msg);
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
        final Region updateR = this.regions.stream().filter(k -> k.getColor() == region.get().getColor()).findFirst().get();
        if(updateR.getNumInfected() == 0) {
            this.incOrDecInfectedPeople(INITIAL_INC, updateR);
        }
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
    public InfoData getInfo() {
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
    public void extractVoyages() {
        final Map<String, Float> pot = new HashMap<>();
        voyageC.getMeans().forEach(k -> {
            switch (k) {
                case "terra":
                    pot.put(k, this.disease.getLandInfectivity());
                    break;
                case "porti":
                    pot.put(k, this.disease.getSeaInfectivity());
                    break;
                case "areporti":
                    pot.put(k, this.disease.getAirInfectivity());
                    break;
                default:
                    break;
            }
        });
        final List<Voyage> voyages = this.voyageC.extractMeans(this.getRegions(), pot);
        if (!voyages.isEmpty()) {
            voyages.forEach(k -> {
                this.incOrDecInfectedPeople(k.getInfected(), getRegionByColor(k.getDest()).get());
                
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
        final Optional<ExtractedEvent> event = this.causeEvents.causeEvent(this.getRegions()
                .stream()
                .filter(k -> k.getCureStatus() != RegionCureStatus.FINISHED)
                .toList());
        if (event.isPresent()) {
            final ExtractedEvent exEvent = event.get();
            final Region exRegion = getRegionByColor(exEvent.getRegion()).get();
            this.incDeathPeople(exEvent.getDeath(), exRegion);
            final Message msg = new Message() {
                @Override
                public MessageType getType() {
                    return MessageType.NEWS;
                }

                @Override
                public String toString() {
                    return exEvent.getEvent() + " in " + exRegion.getName() + " ha causato  "
                            + exEvent.getDeath() + " morti.";
                }
            };
            pcs.firePropertyChange(MessageType.CATASTROPHE.getTitle(), message, msg);
            message = Optional.of(msg);
        }
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
        this.causeEvents = new CauseEventsImpl(events);
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
    public Disease getDisease() {
        return disease;
    }

    @Override
    public Voyages getVoyage() {
        return this.voyageC;
    }

    @Override
    public void setVoyages(final Voyages voyages) {
       this.voyageC = voyages;
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
