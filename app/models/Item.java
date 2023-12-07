package app.models;

/**
 * Represents a checklist item.
 * @author Zarif Mazumder
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

    /**
     * @author Zarif Mazumder
     * @return boolean
     */
    public boolean isDone() {
        return done;
    }

    /**
     * @author Zarif Mazumder
     * @param state boolean
     */
    public void setDone(boolean state) {
        done = state;
    }
}
