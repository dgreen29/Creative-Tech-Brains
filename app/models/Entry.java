package app.models;

import java.math.BigDecimal;

/**
 * Represents a spreadsheet entry.
 * @author Zarif Mazumder
 */
public class Entry {
    /**
     * Stores the cost of the budget entry.
     */
    private BigDecimal cost;
    private int quantity;
    /**
     * Stores the name of the budget entry.
     */
    private String name;

    public Entry(BigDecimal cost, String name, int quantity) {
        this.cost = cost;
        this.name = name;
        this.quantity = quantity;
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
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @author Zarif Mazumder
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        return (this.cost.compareTo(entry.getCost()) == 0 && this.name.equals(this.getName()));
    }
}
