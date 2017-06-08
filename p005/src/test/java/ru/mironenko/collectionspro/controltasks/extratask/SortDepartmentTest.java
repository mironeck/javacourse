package ru.mironenko.collectionspro.controltasks.extratask;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 18.05.2017.
 */
public class SortDepartmentTest {

    SortDepartment sortDepartment = new SortDepartment();
    List<String> departments = new ArrayList<>();

    @Before
    public void init(){

        departments.add("K1\\SK1");
        departments.add("K1\\SK2");
        departments.add("K1\\SK1\\SSK1");
        departments.add("K1\\SK1\\SSK2");
        departments.add("K2");
        departments.add("K2\\SK1\\SSK1");
        departments.add("K2\\SK1\\SSK2");
    }

    @Test
    public void whenAddDepartmentParents(){

        Set<String> result = new TreeSet<>();
        result.add("K1");
        result.add("K1\\SK1");
        result.add("K1\\SK1\\SSK1");
        result.add("K1\\SK1\\SSK2");
        result.add("K1\\SK2");
        result.add("K2");
        result.add("K2\\SK1");
        result.add("K2\\SK1\\SSK1");
        result.add("K2\\SK1\\SSK2");

//        Set<String> checked = sortDepartment.addDepartmentIfNecessaryAndSortAscending(departments);
//
//        assertThat(result, is(checked));

        Set<Department> ascending = sortDepartment.sortDepartmentsAscending(
                sortDepartment.addDepartmentIfNecessaryAndSortAscendingVersionTwo(departments));

        for(Department tmp : ascending) {
            System.out.println(tmp.getName());
        }

        System.out.println();
        Set<Department> descending = sortDepartment.sortDepartmentDescending(ascending);
        for(Department tmp : descending) {
            System.out.println(tmp.getName());
        }
    }


    @Test
    public void whenSortDescendingThenShouldReturnSetFromHighToLow(){

        List<String> checked = new ArrayList<>();
        checked.add("K2");
        checked.add("K2\\SK1");
        checked.add("K2\\SK1\\SSK2");
        checked.add("K2\\SK1\\SSK1");
        checked.add("K1");
        checked.add("K1\\SK2");
        checked.add("K1\\SK1");
        checked.add("K1\\SK1\\SSK2");
        checked.add("K1\\SK1\\SSK1");

        sortDepartment.addDepartmentIfNecessaryAndSortAscendingVersionTwo(departments);

      //  List<String> result

      //  assertThat(result, is(checked));
    }
}