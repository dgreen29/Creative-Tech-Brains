package app.models;

import java.util.LinkedList;

/*
 * Author: Zarif Mazumder
 */

/**
 * Represents a project.
 */
public class Project {
    private final Budget budget;
    private final LinkedList<Item> checklist;
    private final Detail detail;

    public Project() {
        detail = new Detail();
        checklist = new LinkedList<>();
        budget = new Budget();
    }

    public Budget getBudget() {
        return budget;
    }

    public LinkedList<Item> getChecklist() {
        return new LinkedList<>(checklist);
    }

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

    public void setItem(int index, String text) {
        Item item = checklist.get(index);
        item.setText(text);
    }

    /**
     * Removes <code>Item</code> from the checklist.
     * @param index location of <code>Item</code>
     */
    public void removeItem(int index) {
        checklist.remove(index);
    }
}
