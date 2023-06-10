package globaloutbreak.view.scenecontroller;


import globaloutbreak.controller.TypeOfInfo;
import globaloutbreak.model.pair.Pair;
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
import javafx.beans.value.ObservableValue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sun.prism.paint.Color;

/**
 * Map Controller.
 */
public final class MapController extends AbstractSceneController implements SceneInitializer {
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

    private int count = 0;
    private int color;
    private ImageView airportsMap;
    private ImageView portsMap;
    private ImageView sfondo;
    private ImageView buf;
    private String airportPaths;
    private String portPath;
    private Map<String, Map<Pair<Integer, Integer>, Label>> meansPos = new HashMap<>();
    private List<String> visibleMeans;

    @FXML
    public void selectRegion1(final MouseEvent e) {
        final Integer newColor = buf.getImage().getPixelReader().getArgb(
                (int) Math.floor(e.getX() * (sfondo.getImage().getWidth() / sfondo.getFitWidth())),
                (int) Math.floor(e.getY() * (sfondo.getImage().getHeight() / sfondo.getFitHeight())));
        if (!newColor.equals(Color.BLACK.getIntArgbPre())) {
            if (newColor.equals(Color.WHITE.getIntArgbPre()) || !newColor.equals(color)) {
                if (newColor.equals(Color.WHITE.getIntArgbPre())) {
                    this.color = Color.WHITE.getIntArgbPre();
                    mapLab.setGraphic(sfondo);
                    this.getView().selectRegion(newColor);
                }
                if (!newColor.equals(color)) {
                    mapLab.setGraphic(selectedState(newColor));
                    this.color = newColor;
                    this.getView().selectRegion(newColor);
                    Map<TypeOfInfo, String> info = this.getView().getInfoSingleRegion();
                    System.out.println(info.size());
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
            }
        }
    }

    private ImageView selectedState(final int color) {
        WritableImage sfonW = new WritableImage(sfondo.getImage().getPixelReader(),
                (int) Math.floor(sfondo.getImage().getWidth()),
                (int) Math.floor(sfondo.getImage().getHeight()));
        Image bufImage = buf.getImage();
        for (int i = 0; i < (int) Math.floor(bufImage.getWidth()); i++) {
            for (int j = 0; j < (int) Math.floor(bufImage.getHeight()); j++) {
                if (bufImage.getPixelReader().getArgb(i, j) == color) {
                    sfonW.getPixelWriter().setArgb(i, j, -1);
                }
            }
        }
        ImageView i = new ImageView(sfonW);
        resize(i, (int) Math.floor(sfondo.getFitWidth()), (int) Math.floor(sfondo.getFitHeight()));
        return i;
    }

    @FXML
    public void selectRegion(final MouseEvent e) {

    }

    @FXML
    public void openSettings(final MouseEvent e) {
        this.getSceneManager().openSettings();
    }

    @FXML
    public final void goToGeneralGraph(MouseEvent e) {
        this.getSceneManager().openWorldGraphScene();
    }

    @FXML
    public void goToMutation(final MouseEvent e) {

    }

    @FXML
    public void startStop(final MouseEvent e) {
        this.getView().startStop();
        this.setPlayPauseButtonText();
    }

    private ImageView getImage(final String path) {
        return new ImageView(ClassLoader.getSystemResource(path).toString());
    }

    private void resize(final ImageView image, final Integer width, final Integer height) {
        image.setFitHeight(height);
        image.setFitWidth(width);
    }

    private Double percH = 1.0;
    private Double percW = 1.0;
    String path;

    private void resizeIconMeans(final Integer width, final Integer height) {
        path = "";
        percH = 1.0;
        percW = 1.0;
        Double oldH = sfondo.getImage().getHeight();
        Double oldW = sfondo.getImage().getWidth();
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
                }
                m.forEach((p, l) -> {
                    ImageView ik = getImage(path);
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
        int width = (int) Math.floor(im.getImage().getWidth());
        int height = (int) Math.floor(im.getImage().getHeight());
        Map<Pair<Integer, Integer>, Label> temp = new HashMap<>();
        WritableImage r = new WritableImage(im.getImage().getPixelReader(), width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (r.getPixelReader().getArgb(i, j) != -1
                        && r.getPixelReader().getArgb(i, j) != -16777216) {
                    Label label = new Label();
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

    @Override
    public void initializeScene() {
        this.setPlayPauseButtonText();
        if (count == 0) {
            color = Color.WHITE.getIntArgbPre();
            this.airportPaths = "configView/aereo2.png";
            this.portPath = "configView/port2.png";
            this.airportsMap = getImage("configView/airportsMap.png");
            this.portsMap = getImage("configView/ports.png");
            this.buf = getImage("configView/checkRegion.png");

            this.sfondo = getImage("configView/sfon.png");
            visibleMeans = new LinkedList<>();
            visibleMeans.add("porti");
            visibleMeans.add("areoporti");
            setMap();
        }
        resize(sfondo, (int) Math.floor(borderPane.getWidth()), (int) Math.floor(borderPane.getHeight()));
        mapLab.setGraphic(sfondo);
        count++;
        borderPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null && newValue.intValue() != 0 && newValue != oldValue) {
                    int width = newValue.intValue();
                    int height = (int) Math.floor(sfondo.getFitHeight());
                    resizeIconMeans(width, height);
                    resize(sfondo, width, height);
                    resize(buf, width, height);
                    mapLab.setGraphic(sfondo);
                }
                if (newValue != null && newValue.intValue() != 0 && newValue != oldValue) {
                    int width = newValue.intValue();
                    int height = (int) Math.floor(sfondo.getFitHeight());
                    resizeIconMeans(width, height);
                    resize(sfondo, width, height);
                    resize(buf, width, height);
                    mapLab.setGraphic(sfondo);
                }
            }
        });

        borderPane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null && newValue.intValue() != 0 && newValue != oldValue) {
                    // System.out.println((int) Math.floor(
                    // borderPane.getCenter().getLayoutBounds().getHeight()));
                    int width = (int) Math.floor(sfondo.getFitWidth());
                    int height = newValue.intValue();
                    resizeIconMeans(width, height);
                    resize(sfondo, width, height);
                    resize(buf, width, height);
                    mapLab.setGraphic(sfondo);
                }
            }
        });
    }
}
