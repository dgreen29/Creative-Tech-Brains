package app.models;

/**
 * A <code>Project</code>'s description.
 * @author Zarif Mazumder
 */
public class DetailModel {
    public static final String DEFAULT_TEXT = "Write a description of your current project";
    /**
     * String containing the details of the project.
     */
    private String text;

    public DetailModel() {
        text = DEFAULT_TEXT;
    }

    public DetailModel(String text) {
        this.text = text;
    }

    /**
     * @author Zarif Mazumder
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * @author Zarif Mazumder
     * @param text text
     */
    public void setText(String text) {
        this.text = text;
    }
}
