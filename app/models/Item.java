package app.models;

/*
 * Author: Zarif Mazumder
 */

/**
 * Represents a checklist item.
 */
public class Item {
    private String text;
    private boolean done;

    public Item() {
        this.text = "";
        this.done = false;
    }

    public Item(String text) {
        this.text = text;
        this.done = false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean state) {
        done = state;
    }
}
