package ru.mironenko.collectionspro.controltasks.extratask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 02.06.2017.
 */
public class Department{

    private List<String> departments;

    public Department() {
        this.departments = new ArrayList();
    }


    public List<String> getDepartments() {
        return departments;
    }
}
