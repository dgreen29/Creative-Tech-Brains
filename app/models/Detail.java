package app.models;

/*
 * Author: Zarif Mazumder
 */

/**
 * A <code>Project</code>'s description.
 */
public class Detail {
    private String text;

    public Detail() {
        text = "";
    }

    public Detail(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
