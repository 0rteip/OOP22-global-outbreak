package globaloutbreak.model.message;

/**
 * Type of Message.
 */
public enum MessageType {
    /**
     * Cure Message.
     */
    CURE("Cure"),
    /**
     * News Message.
     */
    NEWS("News"),
    /**
     * Catastrophe Message.
     */
    CATASTROPHE("Catastrophe");

    private final String title;

    MessageType(final String title) {
        this.title = title;
    }

    /**
     * Returns the title.
     * 
     * @return
     *         title
     */
    public String getTitle() {
        return this.title;
    }

}
