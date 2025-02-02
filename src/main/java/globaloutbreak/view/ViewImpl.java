package globaloutbreak.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import globaloutbreak.controller.Controller;
import globaloutbreak.controller.region.TypeOfInfo;
import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.message.Message;
import globaloutbreak.model.voyage.Voyage;
import globaloutbreak.settings.gamesettings.GameSettingsGetter;
import globaloutbreak.settings.windowsettings.WindowSettingsImpl;
import globaloutbreak.settings.windowsettings.WindowSettings;
import globaloutbreak.model.disease.DiseaseData;
import globaloutbreak.model.disease.DiseaseDataList;
import globaloutbreak.model.infodata.InfoData;
import globaloutbreak.view.scenemanager.SceneManager;
import globaloutbreak.view.scenemanager.SceneManagerImpl;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Class ViewImpl.
 */
public final class ViewImpl implements View {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WindowSettings settings = new WindowSettingsImpl();
    private final SceneManager manager;
    private final List<Button> diseasesButtons = new ArrayList<>();
    private List<String> mutations = new ArrayList<>();
    private final DiseaseDataList diseasesList = new DiseaseDataList();
    private Controller controller;
    private String desc;
    private Boolean active;
    private int cost;
    private int points;
    private float increase;

    /**
     * Creates a VIewImpl.
     * 
     * @param stage
     *              stage
     */
    public ViewImpl(final Stage stage) {
        this.manager = new SceneManagerImpl(stage, this);
    }

    // @formatter:off
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "We need to use the correct instance of Controller"
    )
    // @formatter:on
    @Override
    public void start(final Controller controller) {
        this.controller = controller;
        logger.info("Starting a new game");
        this.manager.openInitialMenu();
    }

    @Override
    public InfoData getInfoData() {
        return this.controller.displayInfo();
    }

    @Override
    public void displayMessage(final Message message) {
        final CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> this.manager.openMessage(message, latch));
        this.controller.startStop();

        try {
            latch.await();
        } catch (InterruptedException e) {
            this.logger.warn("Latch interrupted", e);
        }

    }

    @Override
    public void displayVoyage(final Voyage voyage) {
        this.logger.info("Voyage from " + voyage.getPart() + " to " + voyage.getDest());
    }

    // @formatter:off
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = """
            We need to access WindowSettings from the SceneLoader, 
            it could be done by calling methods on the view, 
            but we preferred to leave the modification to WindowSettings
        """
    )
    // @formatter:on
    @Override
    public WindowSettings getWindowSettings() {
        return this.settings;
    }

    @Override
    public SceneManager getSceneManager() {
        return this.manager;
    }

    @Override
    public List<Button> getDiseasesButtons() {
        this.controller.readDiseasesNames();
        return List.copyOf(diseasesButtons);
    }

    @Override
    public List<DiseaseData> getDiseasesDatas() {
        this.controller.readDiseasesNames();
        return List.copyOf(diseasesList.getDisease());
    }

    @Override
    public List<String> getMutations() {
        return List.copyOf(mutations);
    }

    @Override
    public String getPoints() {
        return Integer.toString(points);
    }

    @Override
    public String getDescription() {
        return desc;
    }

    @Override
    public String getCost() {
        return String.valueOf(cost);
    }

    @Override
    public String getIncrease() {
        return String.valueOf(increase);
    }
    @Override
    public void setMutationsName(final List<String> mutationsNames) {
        this.mutations = new ArrayList<>(mutationsNames);
    }

    @Override
    public void  setPoints(final int points) {
        this.points = points;
    }
    @Override
    public void setMutationsDesc(final String desc, final Boolean activate, final int cost, final float increase) {
        this.desc = desc;
        this.active = activate;
        this.cost = cost;
        this.increase = increase;
    }

    @Override
    public boolean checkactivate() {
        return active;
    }

    @Override
    public void setDiseasesData(final List<DiseaseData> diseasesNames) {
        diseasesNames.stream().forEach(disease -> diseasesButtons.add(new Button(disease.getType())));
        diseasesList.setDisease(diseasesNames);
    }

    @Override
    public void choosenDisease(final String type) {
        this.controller.choosenDisease(type);
    }

    @Override
    public void choosenNameDisease(final String name) {
        this.controller.choosenDiseaseName(name);
    }

    @Override
    public boolean isGameRunning() {
        return this.controller.isGameRunning();
    }

    @Override
    public void startStop() {
        this.controller.startStop();
    }

    @Override
    public GameSettingsGetter getGameSettings() {
        return this.controller.getSettings();
    }

    @Override
    public void setGameSpeed(final GameSpeed gameSpeed) {
        this.controller.setGameSpeed(gameSpeed);
    }

    @Override
    public void quit() {
        this.controller.quit();
    }

    @Override
    public void displayMutation() {
        this.controller.displayMutationsName();
    }

    @Override
    public void displayPoints() {
        this.controller.displayPoints();
    }

    @Override
    public void displayMutationDesc(final String name) {
        controller.displayMuatationDesc(name);
    }

    @Override
    public void update(final String name) {
        this.controller.update(name);
    }

    @Override
    public Map<TypeOfInfo, String> getInfoSingleRegion() {
        return this.controller.getInfoSingleRegion();
    }

    @Override
    public void selectRegion(final Optional<Integer> color) {
        this.controller.selectedRegion(color);
    }

    @Override
    public void render() {
        this.manager.updateWorld(this);
    }

    @Override
    public List<String> getMeans() {
       return new LinkedList<>(this.controller.getMeans());
    }
}
