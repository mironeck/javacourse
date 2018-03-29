package ru.mironenko.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Role {

    private static final Logger LOG = LoggerFactory.getLogger(Role.class);

    private final int id;
    private final String name;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Role {" +
                " id= " + id +
                ", name = '" + name + '\'' +
                '}';
    }
}
