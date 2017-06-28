package ru.mironenko.collectionspro.controltasks.extratask;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;

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

        Department department = sortDepartment.addDepartmentIfNecessary(departments);

        Set<String> ascending = sortDepartment.sortDepartmentsAscending(department);

        for(String tmp : ascending) {
            System.out.println(tmp);
        }

        System.out.println();

        Set<String> descending = sortDepartment.sortDepartmentDescending(department);
        for(String tmp : descending) {
            System.out.println(tmp);
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

        sortDepartment.addDepartmentIfNecessary(departments);

      //  List<String> result

      //  assertThat(result, is(checked));
    }
}