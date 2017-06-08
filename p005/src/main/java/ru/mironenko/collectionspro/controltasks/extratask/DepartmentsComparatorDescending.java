package ru.mironenko.collectionspro.controltasks.extratask;

import java.util.Comparator;

/**
 * Created by nikita on 04.06.2017.
 */
public class DepartmentsComparatorDescending implements Comparator<Department> {
    @Override
    public int compare(Department o1, Department o2) {

        int k = 0;
        String s1 = o1.getName().substring(0, 2);
        String s2 = o2.getName().substring(0, 2);

//        String s3 = null;
//        String s4 = null;
//
//        if (o1.getName().length() == 6) {
//            s3 = o1.getName().substring(3);
//        }
//        if (o2.getName().length() == 6) {
//            s4 = o2.getName().substring(3);
//        }


        if( !(s1.equals(s2)) ) {
            return o2.getName().compareToIgnoreCase(o1.getName());
        }
        else {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }


    }
}
