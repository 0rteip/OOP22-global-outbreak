package globaloutbreak.controller.api;

import java.io.IOException;

/*
 * Interface that manage the javaFX scenes
 */
public interface MenuController {

    void loadScreen(String fxmlPath) throws IOException;

    void goBack();
}
