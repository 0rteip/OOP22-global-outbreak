package globaloutbreak.view.scenecontroller;

import java.util.Map;

import globaloutbreak.controller.region.TypeOfInfo;

/**
 * Interface for TextFieldSceneSetter.
 */
public interface TextFieldSceneSetter {

    /**
     * Set text to fields.
     * 
     * @param infoSingleRegion
     *                         values map
     */
    void setText(Map<TypeOfInfo, String> infoSingleRegion);

}
