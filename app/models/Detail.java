package app.models;

import java.io.Serializable;

/**
 * A <code>Project</code>'s description.
 * @author Zarif Mazumder
 */
public class Detail implements Serializable {

    private String text; // Description of the project.

    /**
     * Creates an empty <code>Detail</code>.
     */
    public Detail() {
        text = "";
    }

    /**
     * Creates a <code>Detail</code> with the given text.
     * @param text
     */
    public Detail(String text) {
        this.text = text;
    }

    /**
     * Returns the text of the detail.
     * @author Zarif Mazumder
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text of the detail.
     * @author Zarif Mazumder
     * @param text text
     */
    public void setText(String text) {
        this.text = text;
    }
}
