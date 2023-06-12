package globaloutbreak.view.scenecontroller;

import globaloutbreak.controller.TypeOfInfo;
import globaloutbreak.gamespeed.GameSpeed;
import globaloutbreak.model.pair.Pair;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.beans.value.ObservableValue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.prism.paint.Color;

/**
 * Map Controller.
 */
public final class MapController extends AbstractSceneController implements SceneInitializer, SceneUpdater {
    @FXML
    private StackPane mapPane;

    @FXML
    private Button playPausaBut;

    @FXML
    private Label mapLab;

    @FXML
    private Button settingBut;

    @FXML
    private Button mutationBut;

    @FXML
    private TextField infectedText;

    @FXML
    private TextField regionText;

    @FXML
    private TextField deathText;

    @FXML
    private Button worldBut;

    @FXML
    private BorderPane borderPane;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private int count;
    private int color;
    private ImageView airportsMap;
    private ImageView portsMap;
    private ImageView sfondo;
    private ImageView buf;
    private ImageView effectifeImage;
    private String airportPaths;
    private String portPath;
    private Map<String, Map<Pair<Integer, Integer>, Label>> meansPos = new HashMap<>();
    private List<String> visibleMeans;
    private Double percH = 1.0;
    private Double percW = 1.0;
    private String path;
    private Boolean start = false;

    /**
     * Contructor.
     */
    public MapController() {
        count = 0;
    }

    /**
     * This is mapLab's lister.
     * 
     * @param e
     *          Mouse Event (on click)
     */
    @FXML
    public void selectRegion(final MouseEvent e) {
        final Integer newColor = buf.getImage().getPixelReader().getArgb(
                (int) Math.floor(e.getX() * (sfondo.getImage().getWidth() / sfondo.getFitWidth())),
                (int) Math.floor(e.getY() * (sfondo.getImage().getHeight() / sfondo.getFitHeight())));
        if (start) {
            if (!newColor.equals(Color.BLACK.getIntArgbPre())) {
                if (newColor.equals(Color.WHITE.getIntArgbPre())) {
                    this.color = Color.WHITE.getIntArgbPre();
                    effectifeImage = sfondo;
                    mapLab.setGraphic(effectifeImage);
                    this.getView().selectRegion(Optional.empty());
                } else if (!newColor.equals(color)) {
                    selectedState(newColor);
                    mapLab.setGraphic(effectifeImage);
                    this.color = newColor;
                    this.getView().selectRegion(Optional.of(newColor));
                }
            }
        } else {
            if (newColor != Color.WHITE.getIntArgbPre()) {
                this.getView().selectRegion(Optional.of(newColor));
                this.startStopGame();
                selectedState(newColor);
                mapLab.setGraphic(effectifeImage);
                start = true;
            }
        }
        this.setTextFilds(this.getView().getInfoSingleRegion());
    }

    private void setTextFilds(final Map<TypeOfInfo, String> info) {
        info.forEach((t, s) -> {
            if (t.equals(TypeOfInfo.INFETTI)) {
                infectedText.setText(s);
            } else if (t.equals(TypeOfInfo.MORTI)) {
                deathText.setText(s);
            } else if (t.equals(TypeOfInfo.REGION)) {
                regionText.setText(s);
            }
        });
    }

    private void selectedState(final int color) {
        final WritableImage sfonW = new WritableImage(sfondo.getImage().getPixelReader(),
                (int) Math.floor(sfondo.getImage().getWidth()),
                (int) Math.floor(sfondo.getImage().getHeight()));
        final Image bufImage = buf.getImage();
        for (int i = 0; i < (int) Math.floor(bufImage.getWidth()); i++) {
            for (int j = 0; j < (int) Math.floor(bufImage.getHeight()); j++) {
                if (bufImage.getPixelReader().getArgb(i, j) == color) {
                    sfonW.getPixelWriter().setArgb(i, j, -1);
                }
            }
        }
        final ImageView i = new ImageView(sfonW);
        resize(i, (int) Math.floor(sfondo.getFitWidth()), (int) Math.floor(sfondo.getFitHeight()));
        effectifeImage = i;
    }

    /**
     * This method open settings Scene.
     * 
     * @param e
     *          Mouse Event (on click)
     */
    @FXML
    public void openSettings(final MouseEvent e) {
        this.stopGame();
        this.getSceneManager().openSettings();
    }

    /**
     * This method open generalGraph Scene.
     * 
     * @param e
     *          Mouse Event (on click)
     */
    @FXML
    public void goToGeneralGraph(final MouseEvent e) {
        this.stopGame();
        this.getSceneManager().openWorldGraph();
    }

    /**
     * This method open mutation Scene.
     * 
     * @param e
     *          Mouse Event (on click)
     */
    @FXML
    public void goToMutation(final MouseEvent e) {
        this.stopGame();
        this.getSceneManager().openMutation();
    }

    /**
     * This method open star/stop Scene.
     * 
     * @param e
     *          Mouse Event (on click)
     */
    @FXML
    public void startStop(final MouseEvent e) {
        this.startStopGame();
    }

    private ImageView getImage(final String path) {
        return new ImageView(ClassLoader.getSystemResource(path).toString());
    }

    private void resize(final ImageView image, final Integer width, final Integer height) {
        image.setFitHeight(height);
        image.setFitWidth(width);
    }

    private void resizeIconMeans(final Integer width, final Integer height) {
        path = "";
        percH = 1.0;
        percW = 1.0;
        final Double oldH = sfondo.getImage().getHeight();
        final Double oldW = sfondo.getImage().getWidth();
        if (width != 0 && height != 0) {
            percH = height / oldH;
            percW = width / oldW;
            meansPos.forEach((s, m) -> {
                path = "";
                switch (s) {
                    case "areoporti":
                        path = airportPaths;
                        break;
                    case "porti":
                        path = portPath;
                        break;
                    default:
                        break;
                }
                m.forEach((p, l) -> {
                    final ImageView ik = getImage(path);
                    resize(ik, (int) Math.floor(ik.getImage().getWidth() * percW),
                            (int) Math.floor(ik.getImage().getWidth() * percW));
                    ik.setPreserveRatio(true);
                    l.setGraphic(ik);
                    StackPane.setMargin(l,
                            new Insets((int) Math.floor(p.getY() * percH), width - (int) Math.floor(p.getX() * percW),
                                    height - (int) Math.floor(p.getY() * percH), (int) Math.floor(p.getX() * percW)));
                });
            });
        }
    }

    private Map<Pair<Integer, Integer>, Label> extractPos(final ImageView im) {
        final int width = (int) Math.floor(im.getImage().getWidth());
        final int height = (int) Math.floor(im.getImage().getHeight());
        final Map<Pair<Integer, Integer>, Label> temp = new HashMap<>();
        final WritableImage r = new WritableImage(im.getImage().getPixelReader(), width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (r.getPixelReader().getArgb(i, j) != -1
                        && r.getPixelReader().getArgb(i, j) != Color.BLACK.getIntArgbPre()) {
                    final Label label = new Label();
                    this.mapPane.getChildren().add(label);
                    temp.put(new Pair<Integer, Integer>(i, j), label);
                }
            }
        }
        return temp;
    }

    private void setMap() {
        meansPos = new HashMap<>();
        this.visibleMeans.forEach(k -> {
            switch (k) {
                case "areoporti":
                    meansPos.put(k, extractPos(airportsMap));
                    break;
                case "porti":
                    meansPos.put(k, extractPos(portsMap));
                    break;
                default:
                    break;
            }
        });
    }

    private void setPlayPauseButtonText() {
        if (this.getView().isGameRunning()) {
            this.playPausaBut.setText("Stop");
        } else {
            this.playPausaBut.setText("Play");
        }
    }

    /**
     * This method initialize the scene.
     */
    @Override
    public void initializeScene() {
        if (count == 0) {
            color = Color.WHITE.getIntArgbPre();
            this.airportPaths = "configView/aereo2.png";
            this.portPath = "configView/port2.png";
            this.airportsMap = getImage("configView/airportsMap.png");
            this.portsMap = getImage("configView/ports.png");
            this.buf = getImage("configView/checkRegion.png");
            this.sfondo = getImage("configView/sfon.png");
            this.effectifeImage = sfondo;
            visibleMeans = new LinkedList<>();
            visibleMeans.add("porti");
            visibleMeans.add("areoporti");
            setMap();
            this.setTextFilds(this.getView().getInfoSingleRegion());
        }
        resize(sfondo, (int) Math.floor(borderPane.getWidth()), (int) Math.floor(borderPane.getHeight()));
        mapLab.setGraphic(sfondo);
        count++;
        borderPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(final ObservableValue<? extends Number> observable, final Number oldValue,
                    final Number newValue) {
                if (newValue != null && newValue.intValue() != 0 && !newValue.equals(oldValue)) {
                    final int width = newValue.intValue();
                    final int height = (int) Math.floor(sfondo.getFitHeight());
                    resizeIconMeans(width, height);
                    resize(sfondo, width, height);
                    resize(buf, width, height);
                    resize(effectifeImage, width, height);
                    mapLab.setGraphic(effectifeImage);
                }
            }
        });

        borderPane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(final ObservableValue<? extends Number> observable, final Number oldValue,
                    final Number newValue) {
                if (newValue != null && newValue.intValue() != 0 && !newValue.equals(oldValue)) {
                    final int width = (int) Math.floor(sfondo.getFitWidth());
                    final int height = newValue.intValue();
                    resizeIconMeans(width, height);
                    resize(sfondo, width, height);
                    resize(buf, width, height);
                    mapLab.setGraphic(effectifeImage);
                }
            }
        });
        this.setPlayPauseButtonText();
    }

    @Override
    public void updateScene(final GameSpeed gameSpeed) {
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(gameSpeed.getDuration()), event -> {
            // Logica per aggiornare la label
            this.setTextFilds(this.getView().getInfoSingleRegion());
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void stopGame() {
        if (this.getView().isGameRunning()) {
            this.startStopGame();
        }
    }

    private void startStopGame() {
        final boolean status = this.getView().isGameRunning();
        this.getView().startStop();
        this.waitForGameToBe(!status);
    }

    private void waitForGameToBe(final boolean state) {
        while (this.getView().isGameRunning() != state) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                this.logger.warn("Error while waiting for Game to start", e);
            }
        }
        this.setPlayPauseButtonText();
    }
}
