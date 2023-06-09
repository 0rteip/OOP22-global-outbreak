package globaloutbreak.settings.windowsettings;

import java.awt.Toolkit;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;

/**
 * Implementation of {@link WindowSettings}.
 */
public final class WindowSettingsImpl implements WindowSettings {

    private static final int DEFWIDTH = 950;
    private static final int DEFHEIGHT = 500;
    private final MutablePair<Integer, Integer> selectedRes;
    private final ImmutablePair<Integer, Integer> defRes = ImmutablePair.of(DEFWIDTH, DEFHEIGHT);

    /**
     * Creates a {@link WindowSettings} Implementation with a default screen
     * resolution.
     */
    public WindowSettingsImpl() {
        final int selectedWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        final int selectedHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.selectedRes = MutablePair.of(selectedWidth, selectedHeight);
    }

    @Override
    public int getWindowWidth() {
        return this.selectedRes.getKey().intValue();
    }

    @Override
    public int getWindowHeight() {
        return this.selectedRes.getValue().intValue();
    }

    @Override
    public int getDefWindowWidth() { 
        return this.defRes.getKey().intValue();
    }

    @Override
    public int getDefWindowHeight() {
        return this.defRes.getValue().intValue();
    }

    @Override
    public void setWidth(final int width) {
        this.selectedRes.setLeft(width);
    }

    @Override
    public void setHeight(final int height) {
        this.selectedRes.setRight(height);
    }

    @Override
    public String toString() {
        return "WindowSettingsImpl [selectedRes=" + selectedRes + ", defRes=" + defRes + "]";
    }

}
