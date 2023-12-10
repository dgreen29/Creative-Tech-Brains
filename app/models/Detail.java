package app.models;

import java.io.IOException;

/**
 * A <code>Project</code>'s description.
 * @author Zarif Mazumder
 */
public class Detail {
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
     * @param name <code>Project</code> name
     * @param text text
     */
    public void setText(String name, String text) {
        this.text = text;
    }
}
