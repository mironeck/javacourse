package ru.mironenko.collectionspro.controltasks.extratask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 02.06.2017.
 */
public class Department implements Comparable<Department>{

    private List<String> departments = new ArrayList<>();

    private String name;
    private int hierarchy;

    public Department(String name) {
        this.name = name;
    }

    public List<String> getDepartments() {

        return this.departments;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Department o) {
        return name.compareToIgnoreCase(o.getName());
    }
}
