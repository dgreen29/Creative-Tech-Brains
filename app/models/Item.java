package app.models;

import java.io.Serializable;

/**
 * Represents a checklist item.
 * @author Zarif Mazumder
 */
public class Item implements Serializable {

    private String text; // Description of the checklist item.

    private boolean done; // Whether the item is done or not.

    /**
     * Creates an empty <code>Item</code>.
     */
    public Item() {
        this.text = "";
        this.done = false;
    }

    /**
     * Creates an <code>Item</code> with the given text.
     * @param text
     */
    public Item(String text) {
        this.text = text;
        this.done = false;
    }

    /**
     * Creates an <code>Item</code> with the given text and state.
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

    /**
     * Returns whether the item is done or not.
     * @author Zarif Mazumder
     * @return boolean
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Sets the state of the item to done or not done.
     * @author Zarif Mazumder
     * @param state boolean
     */
    public void setDone(boolean state) {
        done = state;
    }
}
