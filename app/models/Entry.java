package app.models;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Represents a spreadsheet entry.
 * @author Zarif Mazumder
 */
public class Entry implements Serializable {
    private double cost;
    private String name;

    public Entry() {
        this.cost = 0.0;
        this.name = "";
    }

    public Entry(String name) {
        this.cost = 0;
        this.name = name;

    }

    public Entry(double cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    /**
     * @author Zarif Mazumder
     * @return cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @author Zarif Mazumder
     * @param cost in USD
     */
    public void setCost(double cost) {
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
        return (this.cost == entry.getCost() && this.name.equals(this.getName()));
    }
}
