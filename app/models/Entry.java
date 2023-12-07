package app.models;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Represents a spreadsheet entry.
 * @author Zarif Mazumder
 */
public class Entry implements Serializable {
    /**
     * Stores the cost of the budget entry.
     */
    private BigDecimal cost;
    /**
     * Stores the name of the budget entry.
     */
    private String name;

    public Entry() {
        this.cost = BigDecimal.ZERO;
        this.name = "";
    }

    public Entry(String name) {
        this.cost = BigDecimal.ZERO;
        this.name = name;

    }

    public Entry(BigDecimal cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    /**
     * @author Zarif Mazumder
     * @return cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * @author Zarif Mazumder
     * @param cost in USD
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
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

    /**
     * @author Zarif Mazumder
     * @return <code>String</code> representation of <code>Entry</code>
     */
    @Override
    public String toString() {
        return cost + name;
    }

    /**
     * @author Zarif Mazumder
     * @param o <code>Object</code> instance of <code>Entry</code>
     * @return true if equal in cost and name
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entry entry)) {
            return false;
        }
        return (this.cost.equals(entry.getCost()) && this.name.equals(this.getName()));
    }
}
