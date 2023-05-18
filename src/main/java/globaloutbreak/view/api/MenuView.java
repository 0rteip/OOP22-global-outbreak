package globaloutbreak.view.api;

import java.io.IOException;
import javafx.scene.input.MouseEvent;

/**
 * Interface that manage menu gui buttons.
 */
public interface MenuView {

    /**
     * Set controller.
     * 
     * @param menuController
     */
    void setController(SceneManager menuController);

    /**
     * Handler on go to Choose name button.
     * 
     * @param evt
     * @throws IOException
     */
    void chooseDiseaseName(MouseEvent evt) throws IOException;

    /**
     * Handler on tutorial button.
     * 
     * @param evt
     * @throws IOException
     */
    void openTutorial(MouseEvent evt) throws IOException;

    /**
     * Handler on quit game button.
     * 
     * @param evt
     * @throws IOException
     */
    void quitGame(MouseEvent evt) throws IOException;

    /**
     * handler on go back button.
     * 
     * @param evt
     * @throws IOException
     */
    void backScene(MouseEvent evt) throws IOException;

    /**
     * Handler on start game button.
     * 
     * @param evt
     * @throws IOException
     */
    void startGame(MouseEvent evt) throws IOException;
}
