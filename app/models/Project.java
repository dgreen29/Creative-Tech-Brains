package app.models;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * Represents a project.
 * @author Zarif Mazumder, Darrell Green, Jr. (DJ Green)
 */
public class Project implements Serializable {

    private final Budget budget; // Budget of the project.

    private final LinkedList<Item> checklist; // Checklist of the project.

    private final Detail detail; // Description of the project.
    private String name; // Name of the project.

    /**
     * Represents a project.
     */
    public Project() {
        detail = new Detail();
        checklist = new LinkedList<>();
        budget = new Budget();
        name = "Project 1";
    }

    /**
     * Returns the budget of the project.
     * @author Zarif Mazumder
     * @return <code>Budget</code>
     */
    public Budget getBudget() {
        return budget;
    }

    /**
     * Returns the checklist of the project.
     * @author Zarif Mazumder
     * @return checklist
     */
    public LinkedList<Item> getChecklist() {
        return new LinkedList<>(checklist);
    }

    /**
     * Returns the description of the project.
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
     * Sets the text of the <code>Item</code> at the given index.
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

    /**
     * Returns the name of the project.
     * @author Zarif Mazumder
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the project.
     * @author Zarif Mazumder
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }
}
