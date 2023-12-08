package app.models;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Represents a project.
 * @author Zarif Mazumder
 */
public class Project implements Serializable {
    /**
     * Stores the budget object corresponding to this project.
     */
    private final Budget budget;
    /**
     * Stores the checklist corresponding to this project.
     */
    private final LinkedList<Item> checklist;
    /**
     * Stores the details object for this project.
     */
    private final Detail detail;
    private String name;

    public Project() {
        detail = new Detail();
        checklist = new LinkedList<>();
        budget = new Budget();
        name = "Project 1";
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

    /**
     * @author Zarif Mazumder
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @author Zarif Mazumder
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }
}
