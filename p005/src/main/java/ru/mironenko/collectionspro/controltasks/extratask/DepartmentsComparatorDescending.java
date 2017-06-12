package ru.mironenko.collectionspro.controltasks.extratask;

import java.util.Comparator;

/**
 * Created by nikita on 04.06.2017.
 */
public class DepartmentsComparatorDescending implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {

        int k = 0;
        String s1 = o1.substring(0, 2);
        String s2 = o2.substring(0, 2);

        if( !(s1.equals(s2)) ) {
            k = o2.compareToIgnoreCase(o1);
        } else {
            k = o1.compareToIgnoreCase(o2);
        }

        return k;
    }
}
