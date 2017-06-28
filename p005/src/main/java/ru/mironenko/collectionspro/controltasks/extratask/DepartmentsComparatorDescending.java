package ru.mironenko.collectionspro.controltasks.extratask;

import java.util.Comparator;

/**
 * Created by nikita on 04.06.2017.
 */
public class DepartmentsComparatorDescending implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {

        int result = 0;
        int one = o1.length();
        int two = o2.length();

        int size = one > two ? one : two;
        for(int index = 0; index != size; index++){
            if(one > index && two > index){
                result = - o1.compareToIgnoreCase(o2);
                if(result != 0){
                    break;
                }
            } else if(one > index){
                result = 1;
                break;
            } else {
                result = -1;
                break;
            }
        }
        return result;
    }
}
