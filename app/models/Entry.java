package app.models;

import java.math.BigDecimal;

/*
 * Author: Zarif Mazumder
 */

/**
 * Represents a spreadsheet entry.
 */
public class Entry {
    private BigDecimal cost;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return cost + name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entry entry)) {
            return false;
        }
        return (this.cost.equals(entry.getCost()) && this.name.equals(this.getName()));
    }
}
