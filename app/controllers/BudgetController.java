package app.controllers;

import app.models.EntryModel;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Handles functionality and communication between <code>BudgetView</code> and the <code>Budget</code> model.
 * @author Zarif Mazumder
 */
public class BudgetController {
    /**
     * Reference to the app's project controller.
     */
    private final ProjectController projectController;

    public BudgetController(ProjectController projectController) {
        this.projectController = projectController;
    }

    /**
     * Adds new entry to the list.
     * @author Zarif Mazumder
     * @param cost in USD
     * @param name name
     */
    public void addEntry(BigDecimal cost, String name, int quantity) {
        projectController.getProject().getBudget().addEntry(new EntryModel(cost, name, quantity));
    }

    public void addEntry(EntryModel entry) {
        projectController.getProject().getBudget().addEntry(entry);
    }

    /**
     * Modifies given <code>Entry</code>.
     * @author Zarif Mazumder
     * @param index location of <code>Entry</code>
     * @param cost in USD
     * @param name name
     */
    public void setEntry(int index, BigDecimal cost, String name, int quantity) {
        projectController.getProject().getBudget().setEntry(index, cost, name, quantity);
    }

    public void setEntry(int index, EntryModel entry) {
        projectController.getProject().getBudget().setEntry(index, entry.getCost(), entry.getName(), entry.getQuantity());
    }

    /**
     * Removes <code>Entry</code> from the list.
     * @author Zarif Mazumder
     * @param index location of <code>Entry</code>
     */
    public void removeEntry(int index) {
        projectController.getProject().getBudget().removeEntry(index);
    }

    /**
     * @author Zarif Mazumder
     * @return LinkedList&lt;Entry&gt; of entries
     */
    public LinkedList<EntryModel> getEntries() {
        return projectController.getProject().getBudget().getEntries();
    }
}
