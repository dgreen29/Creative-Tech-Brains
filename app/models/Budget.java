package app.models;

import java.math.BigDecimal;
import java.util.LinkedList;

/*
 * Author: Zarif Mazumder
 */

/**
 * Represents a list of items and their costs.
 */
public class Budget {
    private final LinkedList<Entry> entries;

    public Budget() {
        entries = new LinkedList<>();
    }

    public LinkedList<Entry> getEntries() {
        return new LinkedList<>(entries);
    }

    /**
     * Adds <code>Entry</code> to the list.
     * @param entry entry(cost, name)
     */
    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    public void setEntry(int index, BigDecimal cost, String name) {
        Entry entry = entries.get(index);
        entry.setCost(cost);
        entry.setName(name);
    }

    /**
     * Removes <code>Entry</code> from the list.
     * @param index location of <code>Entry</code>
     */
    public void removeEntry(int index) {
        entries.remove(index);
    }
}
