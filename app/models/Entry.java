package app.models;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Represents a spreadsheet entry.
 * @author Zarif Mazumder, Darrell Green, Jr.
 */
public class Entry implements Serializable {

    private BigDecimal cost; // Cost of the budget entry.

    private String name; // Name of the budget entry.

    /**
     * Creates an empty <code>Entry</code>.
     */
    public Entry() {
        this.cost = BigDecimal.ZERO;
        this.name = "";
    }

    /**
     * Creates an <code>Entry</code> with the given name.
     * @param name
     */
    public Entry(String name) {
        this.cost = BigDecimal.ZERO;
        this.name = name;

    }

    /**
     * Creates an <code>Entry</code> with the given cost and
     * name.
     * @param cost
     * @param name
     */
    public Entry(BigDecimal cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    /**
     * Returns the cost of the entry.
     * @author Zarif Mazumder
     * @return cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Sets the cost of the entry.
     * @author Zarif Mazumder
     * @param cost in USD
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Returns the name of the entry.
     * @author Zarif Mazumder
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the entry.
     * @author Zarif Mazumder
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the <code>String</code> representation of the
     * <code>Entry</code> in the format of "cost name".
     * @author Zarif Mazumder
     * @return <code>String</code> representation of <code>Entry</code>
     */
    @Override
    public String toString() {
        return cost + name;
    }

    /**
     * Returns true if the given <code>Object</code> is an
     * <code>Entry</code> with the same cost and name as this
     * <code>Entry</code>.
     * @author Zarif Mazumder
     * @param o <code>Object</code> instance of <code>Entry</code>
     * @return true if equal in cost and name
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entry entry)) {
            return false;
        }
        return (this.cost.compareTo(entry.getCost()) == 0 && this.name.equals(this.getName()));
    }
}
