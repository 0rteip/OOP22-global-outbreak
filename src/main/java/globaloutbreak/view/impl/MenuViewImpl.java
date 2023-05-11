package globaloutbreak.view.impl;

import java.io.IOException;

import globaloutbreak.controller.api.MenuController;
import globaloutbreak.controller.impl.MenuControllerImpl;
import globaloutbreak.view.api.MenuView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

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

	private MenuController menuController;

	/*
	 * Go to choose disease name Gui
	 */
	@FXML
	public final void chooseDiseaseName(final MouseEvent evt) {
		try {
			menuController.loadScreen("layouts/DiseaseNameGui.fxml");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/*
	 * Go to tutorial Gui
	 */
	@FXML
	public final void openTutorial(final MouseEvent evt) {
		try {
			menuController.loadScreen("layouts/TutorialGui.fxml");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/*
	 * Quit game
	 */
	@FXML
	public final void quitGame(final MouseEvent evt) {
		Platform.exit();
	}

	/*
	 * Go to the previous scene
	 */
	@FXML
	public final void backScene(final MouseEvent evt) {
		menuController.goBack();
	}

	/**
	 * Start the game
	 */
	@FXML
	public final void startGame(final MouseEvent evt) {

	}

	/**
	 * Set the menu scene controller
	 */
	@Override
	public void setController(MenuController menuController) {
		if (menuController == null) {
			throw new IllegalArgumentException("MenuController cannot be null");
		}
		if (menuController instanceof MenuControllerImpl) {
			this.menuController = (MenuControllerImpl) menuController;
		} else {
			throw new IllegalArgumentException("MenuController must implement MenuView");
		}
	}
}
