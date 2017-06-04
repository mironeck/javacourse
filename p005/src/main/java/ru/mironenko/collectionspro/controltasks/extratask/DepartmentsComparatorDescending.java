package ru.mironenko.collectionspro.controltasks.extratask;

import java.util.Comparator;

/**
 * Created by nikita on 04.06.2017.
 */
public class DepartmentsComparatorDescending implements Comparator<Department> {
    @Override
    public int compare(Department o1, Department o2) {
        return o2.getName().compareToIgnoreCase(o1.getName());
    }
}
