package view.utilities;

public enum SceneStyle {

    INITIALMENU("Initial Menu", "layouts/MenuGui.fxml"),
    /**
     * map scene.
     */
    // CHOOSEDISEASE("Choose Disease", "layouts/ChooseDisease.fxml"),
    /**
     * Settings scene.
     */
    DISEASENAME("Choose disease name", "layouts/DiseaseNameGui.fxml"),
    /**
     * Settings scene.
     */
    TUTORIAL("Tutorial", "layouts/TutorialGui.fxml");

    private final String fxmlFile;
    private final String title;

    SceneStyle(final String title, final String fxmlFile) {
        this.title = title;
        this.fxmlFile = fxmlFile;
    }

    /**
     * @return the fxml file of the scene
     */
    public String getFxmlFile() {
        return this.fxmlFile;
    }

    /**
     * @return the fxml path of the scene
     */
    public String getTitle() {
        return this.title;
    }
}
