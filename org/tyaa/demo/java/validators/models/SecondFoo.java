package org.tyaa.demo.java.validators.models;

public class SecondFoo {
    private Double price;
    public boolean selected;

    public SecondFoo(Double price, boolean selected) {
        this.price = price;
        this.selected = selected;
    }

    @Override
    public String toString() {
        return String.format("price = %s; selected = %s", price, selected);
    }
}
