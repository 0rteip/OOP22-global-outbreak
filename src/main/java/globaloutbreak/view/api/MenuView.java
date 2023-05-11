package globaloutbreak.view.api;

import java.io.IOException;

import globaloutbreak.controller.api.MenuController;
import javafx.scene.input.MouseEvent;

/*
 * interface that manage menu gui buttons
 */
public interface MenuView {

    void setController(MenuController menuController);

    void chooseDiseaseName(MouseEvent evt) throws IOException;

    void openTutorial(MouseEvent evt) throws IOException;

    void quitGame(MouseEvent evt) throws IOException;

    void backScene(MouseEvent evt) throws IOException;

    void startGame(MouseEvent evt) throws IOException;
}
