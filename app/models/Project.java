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
    private final BudgetModel budgetModel;
    /**
     * Stores the checklist corresponding to this project.
     */
    private final LinkedList<ItemModel> checklist;
    /**
     * Stores the details object for this project.
     */
    private final DetailModel detailModel;
    private String name;

    public Project(String name) {
        detailModel = new DetailModel();
        checklist = new LinkedList<>();
        budgetModel = new BudgetModel();
        this.name = name;
    }

    public Project(ProjectBuilder projectBuilder) {
        this.detailModel = projectBuilder.detailModel;
        this.checklist = projectBuilder.checklist;
        this.budgetModel = projectBuilder.budgetModel;
        this.name = projectBuilder.name;
    }

    /**
     * @author Zarif Mazumder
     * @return <code>Budget</code>
     */
    public BudgetModel getBudget() {
        return budgetModel;
    }

    /**
     * @author Zarif Mazumder
     * @return checklist
     */
    public LinkedList<ItemModel> getChecklist() {
        return checklist;
    }

    /**
     * @author Zarif Mazumder
     * @return <code>Detail</code>
     */
    public DetailModel getDetail() {
        return detailModel;
    }

    /**
     * Adds <code>Item</code> to the checklist.
     * @param text content of <code>Item</code>
     */
    public void addItem(String text) {
        checklist.add(new ItemModel(text));
    }

    /**
     * @author Zarif Mazumder
     * @param index location of item
     * @param text text
     */
    public void setItem(int index, String text, boolean isDone) {
        ItemModel item = checklist.get(index);
        item.setText(text);
        item.setDone(isDone);
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
        private DetailModel detailModel = new DetailModel();
        private LinkedList<ItemModel> checklist = new LinkedList<>();
        private BudgetModel budgetModel = new BudgetModel();

        public ProjectBuilder(String name) {
            this.name = name;
        }

        public void setDetail(DetailModel detailModel) {
            this.detailModel = detailModel;
        }

        public void setChecklist(LinkedList<ItemModel> checklist) {
            this.checklist = checklist;
        }

        public void setBudget(BudgetModel budgetModel) {
            this.budgetModel = budgetModel;
        }

        public Project build() {
            return new Project(this);
        }
    }
}
