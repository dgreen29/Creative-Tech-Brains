package app.models;

/**
 * Represents a checklist item.
 * @author Zarif Mazumder
 */
public class ItemModel {
    /**
     * String storing the checklist item's text.
     */
    private String text;
    /**
     * Boolean storing whether the checklist item is completed.
     */
    private boolean isDone;

    public ItemModel(String text) {
        this.text = text;
        this.isDone = false;
    }

    public ItemModel(String text, boolean isDone) {
        this.text = text;
        this.isDone = isDone;
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
        return isDone;
    }

    /**
     * @author Zarif Mazumder
     * @param state boolean
     */
    public void setDone(boolean state) {
        isDone = state;
    }
}
