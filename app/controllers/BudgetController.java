package app.controllers;

import app.models.Entry;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Handles functionality and communication between <code>BudgetView</code> and the <code>Budget</code> model.
 * @author Zarif Mazumder
 */
public class BudgetController {
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
    public void addEntry(BigDecimal cost, String name) {
        projectController.getProject().getBudget().addEntry(new Entry(cost, name));
    }

    /**
     * Modifies given <code>Entry</code>.
     * @author Zarif Mazumder
     * @param index location of <code>Entry</code>
     * @param cost in USD
     * @param name name
     */
    public void setEntry(int index, BigDecimal cost, String name) {
        projectController.getProject().getBudget().setEntry(index, cost, name);
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
    public LinkedList<Entry> getEntries() {
        return projectController.getProject().getBudget().getEntries();
    }
}
