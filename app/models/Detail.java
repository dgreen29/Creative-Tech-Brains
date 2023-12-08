package app.models;

import java.io.Serializable;

/**
 * A <code>Project</code>'s description.
 * @author Zarif Mazumder
 */
public class Detail implements Serializable {
    /**
     * String containing the details of the project.
     */
    private String text;

    public Detail() {
        text = "";
    }

    public Detail(String text) {
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
