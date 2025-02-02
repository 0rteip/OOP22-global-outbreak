package globaloutbreak.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import globaloutbreak.model.endcauses.EndCauses;
import globaloutbreak.model.cure.Cure;
import globaloutbreak.model.dataanalyzer.DataAnalyzer;
import globaloutbreak.model.dataanalyzer.DeathNumberAnalyzer;
import globaloutbreak.model.cure.RegionCureStatus;
import globaloutbreak.model.cure.observer.DiseaseObserver;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.events.CauseEvent;
import globaloutbreak.model.events.CauseEventsImpl;
import globaloutbreak.model.events.Event;
import globaloutbreak.model.events.ExtractedEvent;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.message.MessageType;
import globaloutbreak.model.observer.InfoDataRegionObserver;
import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.model.infodata.InfoDataImpl;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.voyage.Voyage;
import globaloutbreak.model.voyage.Voyages;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Impl of Model interface.
 */

public final class ModelImpl implements Model {

    private List<Voyage> voyages = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Disease disease;
    private List<Region> regions = new LinkedList<>();
    private Optional<Region> selectedRegion = Optional.empty();
    private Voyages voyageC;
    private Optional<Cure> cure = Optional.empty();
    private final DataAnalyzer<Long> deathAnalyzer;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Optional<Message> message = Optional.empty();
    private CauseEvent causeEvents;
    private InfoData infoData;
    private static final long INITIAL_INC = 1;
    private boolean isDiseaseSpreading;
    private Optional<EndCauses> endCause = Optional.empty();

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
    public void addListener(final PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    @Override
    public List<Region> getRegions() {
        return new LinkedList<>(this.regions);
    }

    @Override
    public void selectedRegion(final Optional<Region> region) {
        this.selectedRegion = region;
        if (this.selectedRegion.isPresent() && !this.isDiseaseSpreading) {
            final Region updateR = this.regions.stream()
                    .filter(k -> k.getColor() == region.get().getColor())
                    .findFirst().get();
            updateR.incOrDecInfectedPeople(INITIAL_INC);
            this.isDiseaseSpreading = !this.isDiseaseSpreading;
            this.logger.info("Disease started spreading");
        }
    }

    // @formatter:off
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "We need to use the correct instance of Disease"
    )
    // @formatter:on
    @Override
    public void setDisease(final Disease disease) {
        this.disease = disease;
        this.cure.ifPresent(cure -> this.disease.initializeCureObserver(new DiseaseObserver(cure)));
    }

    @Override
    public void setDiseaseName(final String name) {
        this.disease.setName(name);
    }

    @Override
    public boolean isDiseaseSet() {
        return !Objects.isNull(this.disease);
    }

    @Override
    public InfoData getInfo() {
        return this.infoData;
    }

    @Override
    public Optional<Region> getSelectedRegion() {
        return this.selectedRegion;
    }

    private void extractVoyages() {
        final Map<String, Float> pot = new HashMap<>();
        voyageC.getMeans().forEach(k -> {
            switch (k) {
                case "terra":
                    pot.put(k, this.disease.getLandInfectivity());
                    break;
                case "porti":
                    pot.put(k, this.disease.getSeaInfectivity());
                    break;
                case "aereoporti":
                    pot.put(k, this.disease.getAirInfectivity());
                    break;
                default:
                    break;
            }
        });

        final List<Voyage> voyages = this.voyageC.extractMeans(this.getRegions(), pot);
        this.voyages = List.copyOf(voyages);
        if (!voyages.isEmpty()) {
            voyages.forEach(k -> {
                this.incOrDecInfectedPeople(k.getInfected(), this.getUpdateRegion(k.getDest()).get());
            });
        }
    }

    private Optional<Region> getUpdateRegion(final int region) {
        return this.getRegions().stream()
                .filter(k -> k.getColor() == region)
                .findFirst();
    }

    private void incOrDecInfectedPeople(final long newinfected, final Region region) {
        region.incOrDecInfectedPeople(newinfected);
    }

    private void causeEvent() {
        final Optional<ExtractedEvent> event = this.causeEvents.causeEvent(this.getRegions()
                .stream()
                .filter(k -> !k.getCureStatus().equals(RegionCureStatus.FINISHED))
                .toList());
        if (event.isPresent()) {
            final ExtractedEvent exEvent = event.get();
            final Region exRegion = getUpdateRegion(exEvent.getRegion()).get();
            exRegion.incDeathPeople(exEvent.getDeath(), true);
            final Message msg = new Message() {
                @Override
                public MessageType getType() {
                    return MessageType.CATASTROPHE;
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
        this.initializeInfoData();
    }

    @Override
    public void setEvents(final List<Event> events) {
        this.causeEvents = new CauseEventsImpl(events);
    }

    @Override
    public void setCure(final Cure cure) {
        this.cure = Optional.of(cure);
        this.cure.get().addAction((value) -> {
            final Message msg = new Message() {
                @Override
                public MessageType getType() {
                    return MessageType.CURE;
                }

                @Override
                public String toString() {
                    return "Cure reach " + value + "%";
                }
            };
            pcs.firePropertyChange(MessageType.CURE.getTitle(), message, msg);
            message = Optional.of(msg);
        });
    }

    @Override
    public Optional<EndCauses> getEndCause() {
        return this.endCause;
    }

    @Override
    public boolean isGameOver() {
        if (this.cure.isPresent()) {
            if (this.cure.get().isCompleted()) {
                this.endCause = Optional.of(EndCauses.CURE_DEVELOPED);
            } else if (this.infoData.getTotalDeaths() == this.infoData.getTotalPopulation()) {
                this.endCause = Optional.of(EndCauses.POPULATION_ANNIHILATED);
            } else if (this.infoData.getTotalInfected() == 0 && this.infoData.getTotalDeaths() > 0) {
                this.endCause = Optional.of(EndCauses.NO_INFECTED);
            }
            return this.endCause.isPresent();
        }
        logger.info("No Cure setted, closing game");
        return true;
    }

    // @formatter:off
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "We need to use the correct instance of Disease"
    )
    // @formatter:on
    @Override
    public Disease getDisease() {
        return this.disease;
    }

    @Override
    public Voyages getVoyage() {
        return this.voyageC;
    }

    @Override
    public void setVoyages(final Voyages voyages) {
        this.voyageC = voyages;
    }

    private void initializeInfoData() {
        this.infoData = new InfoDataImpl(this.regions.stream()
                .map(Region::getPopTot)
                .reduce(0L, (e0, e1) -> e0 + e1));
        this.regions.forEach(region -> {
            region.initializeObserver(new InfoDataRegionObserver(this.infoData));
        });
    }

    @Override
    public List<Voyage> getVoyages() {
        return new LinkedList<>(this.voyages);
    }

    @Override
    public void update() {
        this.extractVoyages();
        this.disease.killPeopleRegions(this.regions);
        this.disease.infectRegions(this.regions);
        this.causeEvent();
        this.deathAnalyzer.analyze(this.infoData.getTotalDeaths());
        this.cure.ifPresent(cure -> cure.research());
        this.infoData.updateTotalDeathsAndInfected(this.regions);
        this.infoData.updateCureData(this.cure.get().getGlobalStatus());
    }

    @Override
    public List<String> getMeans() {
       return new LinkedList<>(voyageC.getMeans());
    }
}
