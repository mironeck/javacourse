package ru.mironenko.collectionspro.controltasks.extratask;

import java.util.Comparator;

/**
 * Created by nikita on 02.06.2017.
 */
public class DepartmentsComparatorAscending implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareToIgnoreCase(o2);
    }
}
