package globaloutbreak.view.impl;

import java.io.IOException;

import globaloutbreak.view.api.SceneManager;
import globaloutbreak.view.api.MenuView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Class that manage button handlers.
 */
public class MenuViewImpl implements MenuView {

    @FXML
    private Button newGameButton;

    @FXML
    private Button tutorialButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button backButton;

    @FXML
    private Button submitButton;

    private SceneManager sceneManager;

    /**
     * Go to choose disease name Gui.
     */
    @FXML
    @Override
    public final void chooseDiseaseName(final MouseEvent evt) {
        try {
            if (this.sceneManager != null) {
                sceneManager.loadScreen("scelta nome");
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    /**
     * Go to tutorial Gui.
     * 
     * @param evt event handler
     */
    @FXML
    @Override
    public final void openTutorial(final MouseEvent evt) {
        try {
            if (this.sceneManager != null) {
                sceneManager.loadScreen("tutorial");
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    /**
     * Quit game.
     */
    @FXML
    @Override
    public final void quitGame(final MouseEvent evt) {
        Platform.exit();
    }

    /**
     * Go to the previous scene.
     * 
     * @param evt event handler
     */
    @FXML
    @Override
    public final void backScene(final MouseEvent evt) {
        if (this.sceneManager != null) {
            sceneManager.goBack();
        }
    }

    /**
     * Start the game.
     * 
     * @param evt event handler
     */
    @FXML
    @Override
    public final void startGame(final MouseEvent evt) {

    }

    /**
     * Set the scene Manager.
     * 
     * @param sceneManager
     */
    @Override
    public void setController(final SceneManager sceneManager) {
        if (sceneManager == null) {
            throw new IllegalArgumentException("MenuController cannot be null");
        }
        if (sceneManager instanceof SceneManagerImpl) {
            this.sceneManager = (SceneManagerImpl) sceneManager;
        } else {
            throw new IllegalArgumentException("MenuController must implement MenuView");
        }
    }
}
