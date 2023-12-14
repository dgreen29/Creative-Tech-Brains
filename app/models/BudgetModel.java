package app.models;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Represents a list of items and their costs.
 * @author Zarif Mazumder
 */
public class BudgetModel {
    /**
     * The linked list that stores all the budget entries in a project.
     */
    private LinkedList<EntryModel> entries;

    public BudgetModel() {
        entries = new LinkedList<>();
    }

    /**
     * @author Zarif Mazumder
     * @return LinkedList&lt;Entry&gt; of entries
     */
    public LinkedList<EntryModel> getEntries() {
        return entries;
    }

    /**
     * @author Zarif Mazumder
     * @param entries LinkedList&lt;Entry&gt; of entries
     */
    public void setEntries(LinkedList<EntryModel> entries) {
        this.entries = entries;
    }

    /**
     * Adds <code>Entry</code> to the list.
     * @author Zarif Mazumder
     * @param entryModel entry(cost, name)
     */
    public void addEntry(EntryModel entryModel) {
        entries.add(entryModel);
    }


    /**
     * Modifies given <code>Entry</code>.
     * @author Zarif Mazumder
     * @param index location of <code>Entry</code>
     * @param cost in USD
     * @param name name
     */
    public void setEntry(int index, BigDecimal cost, String name, int quantity) {
        EntryModel entryModel = entries.get(index);
        entryModel.setCost(cost);
        entryModel.setName(name);
        entryModel.setQuantity(quantity);
    }

    /**
     * Removes <code>Entry</code> from the list.
     * @author Zarif Mazumder
     * @param index location of <code>Entry</code>
     */
    public void removeEntry(int index) {
        entries.remove(index);
    }
}
