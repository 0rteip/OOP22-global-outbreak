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

    final String title;

    private MessageType(final String title) {
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
