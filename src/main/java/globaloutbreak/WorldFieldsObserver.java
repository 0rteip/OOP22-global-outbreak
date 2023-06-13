package globaloutbreak;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import globaloutbreak.controller.TypeOfInfo;

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
            @SuppressWarnings("unchecked")
            final Map<TypeOfInfo, String> valueMap = (Map<TypeOfInfo, String>) newValue;
            this.textFieldSceneSetter.setText(valueMap);
        }
    }

}
