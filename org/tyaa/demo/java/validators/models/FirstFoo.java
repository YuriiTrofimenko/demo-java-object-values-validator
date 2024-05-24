package org.tyaa.demo.java.validators.models;

public class FirstFoo {
    private final Integer id;
    public String name;

    public FirstFoo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("id = %s; name = %s", id, name);
    }
}
