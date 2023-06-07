package settings;

/**
 * Interface that manage window settings.
 */
public interface WindowSettings {

    /**
     * @return the window width
     */
    int getWindowWidth();

    /**
     * @return the window height
     */
    int getWindowHeight();

    /**
     * @return the default window width
     */
    int getDefWindowWidth();

    /**
     * @return the default window height
     */
    int getDefWindowHeight();

    /**
     * Set the current width.
     * 
     * @param width
     *              width
     */
    void setWidth(int width);

    /**
     * Set the current height.
     * 
     * @param height
     *               height
     */
    void setHeight(int height);
}
