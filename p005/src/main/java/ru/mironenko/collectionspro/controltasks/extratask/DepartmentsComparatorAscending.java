package ru.mironenko.collectionspro.controltasks.extratask;

import java.util.Comparator;

/**
 * Created by nikita on 02.06.2017.
 */
public class DepartmentsComparatorAscending implements Comparator<Department> {
    @Override
    public int compare(Department o1, Department o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
