package globaloutbreak.view.scenemanager;

import javafx.stage.Stage;

/**
 * Interface that manage the javaFX scenes.
 */
public interface SceneManager {

    public void openInitialMenu(final Stage stage);

    public void openTutorial(final Stage stage);

    public void openDiseaseChoice(final Stage stage);

    public void openDiseaseName(final Stage stage);

    public void openBackScene(final Stage stage);
}
