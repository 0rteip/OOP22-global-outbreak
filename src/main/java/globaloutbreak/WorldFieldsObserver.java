package globaloutbreak;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import globaloutbreak.controller.TypeOfInfo;

/**
 * WorldFields observer.
 */
public class WorldFieldsObserver implements PropertyChangeListener {

    final private TextFieldSceneSetter textFieldSceneSetter;

    /**
     * Create an observer.
     * 
     * @param textFieldSceneSetter
     *                             the TextFieldSceneSetter
     */
    public WorldFieldsObserver(final TextFieldSceneSetter textFieldSceneSetter) {
        this.textFieldSceneSetter = textFieldSceneSetter;
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        Object newValue = arg0.getNewValue();
        if (newValue instanceof Map) {
            Map<TypeOfInfo, String> valueMap = (Map<TypeOfInfo, String>) newValue;
            this.textFieldSceneSetter.setText(valueMap);
        }
    }

}
