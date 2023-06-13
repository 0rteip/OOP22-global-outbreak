package globaloutbreak.view.scenecontroller.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import globaloutbreak.controller.region.TypeOfInfo;
import globaloutbreak.view.scenecontroller.TextFieldSceneSetter;

/**
 * WorldFields observer.
 */
public final class WorldFieldsObserver implements PropertyChangeListener {

    private final TextFieldSceneSetter textFieldSceneSetter;

    /**
     * Create an observer.
     * 
     * @param textFieldSceneSetter
     *                             the TextFieldSceneSetter
     */
    // @formatter:off
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "We need to use the correct instance of the TextFieldSceneSetter"
    )
    // @formatter:on
    public WorldFieldsObserver(final TextFieldSceneSetter textFieldSceneSetter) {
        this.textFieldSceneSetter = textFieldSceneSetter;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent arg0) {
        final Object newValue = arg0.getNewValue();
        if (newValue instanceof Map) {
            for (final Map.Entry<?, ?> entry : ((Map<?, ?>) newValue).entrySet()) {
                if (!(entry.getKey() instanceof TypeOfInfo) || !(entry.getValue() instanceof String)) {
                    // Is not a correct instace
                    return;
                }
            }
            // I am sure it is correct
            @SuppressWarnings("unchecked")
            final Map<TypeOfInfo, String> typedMap = (Map<TypeOfInfo, String>) newValue;
            this.textFieldSceneSetter.setText(typedMap);
        }
    }

}
