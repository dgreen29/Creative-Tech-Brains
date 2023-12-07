package app.models;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Represents a project.
 * @author Zarif Mazumder
 */
public class Project implements Serializable {
    private final Budget budget;
    private final LinkedList<Item> checklist;
    private final Detail detail;

    public Project() {
        detail = new Detail();
        checklist = new LinkedList<>();
        budget = new Budget();
    }

    /**
     * @author Zarif Mazumder
     * @return <code>Budget</code>
     */
    public Budget getBudget() {
        return budget;
    }

    /**
     * @author Zarif Mazumder
     * @return checklist
     */
    public LinkedList<Item> getChecklist() {
        return new LinkedList<>(checklist);
    }

    /**
     * @author Zarif Mazumder
     * @return <code>Detail</code>
     */
    public Detail getDetail() {
        return detail;
    }

    /**
     * Adds <code>Item</code> to the checklist.
     * @param text content of <code>Item</code>
     */
    public void addItem(String text) {
        checklist.add(new Item(text));
    }

    /**
     * @author Zarif Mazumder
     * @param index location of item
     * @param text text
     */
    public void setItem(int index, String text) {
        Item item = checklist.get(index);
        item.setText(text);
    }

    /**
     * Removes <code>Item</code> from the checklist.
     * @author Zarif Mazumder
     * @param index location of <code>Item</code>
     */
    public void removeItem(int index) {
        checklist.remove(index);
    }
}
