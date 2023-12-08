package app.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Represents a list of items and their costs.
 * @author Zarif Mazumder
 */
public class Budget implements Serializable {
    /**
     * The linked list that stores all the budget entries in a project.
     */
    private final LinkedList<Entry> entries;

    public Budget() {
        entries = new LinkedList<>();
    }

    /**
     * @author Zarif Mazumder
     * @return LinkedList&lt;Entry&gt; of entries
     */
    public LinkedList<Entry> getEntries() {
        return new LinkedList<>(entries);
    }

    /**
     * Adds <code>Entry</code> to the list.
     * @author Zarif Mazumder
     * @param entry entry(cost, name)
     */
    public void addEntry(Entry entry) {
        entries.add(entry);
    }


    /**
     * Modifies given <code>Entry</code>.
     * @author Zarif Mazumder
     * @param index location of <code>Entry</code>
     * @param cost in USD
     * @param name name
     */
    public void setEntry(int index, BigDecimal cost, String name) {
        Entry entry = entries.get(index);
        entry.setCost(cost);
        entry.setName(name);
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
