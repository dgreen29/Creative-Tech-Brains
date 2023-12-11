package app.models;

import java.util.LinkedList;

/**
 * Represents a project.
 * @author Zarif Mazumder
 */
public class Project {
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

    public Project(String name) {
        detail = new Detail();
        checklist = new LinkedList<>();
        budget = new Budget();
        this.name = name;
    }

    public Project(ProjectBuilder projectBuilder) {
        this.detail = projectBuilder.detail;
        this.checklist = projectBuilder.checklist;
        this.budget = projectBuilder.budget;
        this.name = projectBuilder.name;
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
        return checklist;
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

    public static class ProjectBuilder {
        private final String name;
        private Detail detail = new Detail();
        private LinkedList<Item> checklist = new LinkedList<>();
        private Budget budget = new Budget();

        public ProjectBuilder(String name) {
            this.name = name;
        }

        public void setDetail(Detail detail) {
            this.detail = detail;
        }

        public void setChecklist(LinkedList<Item> checklist) {
            this.checklist = checklist;
        }

        public void setBudget(Budget budget) {
            this.budget = budget;
        }

        public Project build() {
            return new Project(this);
        }
    }
}
