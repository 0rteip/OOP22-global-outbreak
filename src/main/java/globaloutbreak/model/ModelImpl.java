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
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.events.Event;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.message.MessageType;
import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.region.RegionImpl;
import globaloutbreak.model.voyage.Voyage;
import globaloutbreak.model.voyage.VoyageImpl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;

/**
 * Impl of Model interface.
 */

public class ModelImpl implements Model {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Disease disease;
    private final List<Region> regions = new LinkedList<>();
    private Optional<Region> selectedRegion = Optional.empty();
    private Voyage voyage;
    private Optional<Cure> cure = Optional.empty();
    private final List<Event> events = new LinkedList<>();
    private final DataAnalyzer<Integer> deathAnalyzer;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Optional<Message> newsMessage = Optional.empty();

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
    public void addNesListener(final PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void addRegion(final Integer ppTot, final String name,
            final Map<String, Pair<Integer, Optional<List<String>>>> reachableRegion, final float urban,
            final float poor, final Integer color, final Integer facilities, final float hot, final float humid) {
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
    public void setDisease(final Disease disease) {
        this.disease = disease;
    }

    @Override
    public void setDiseaseName(final String name) {
        this.disease.setName(name);
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
}
