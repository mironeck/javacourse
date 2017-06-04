package ru.mironenko.collectionspro.controltasks.extratask;

import java.util.*;

/**
 * Created by nikita on 17.05.2017.
 */
public class SortDepartment {

    Set<Department> departmentSet = new TreeSet<>();


    public Set<Department> getDepartmentSet() {
        return this.departmentSet;
    }

    /**
     * Adds departments and sorts it in natural sorting in set
     * @param departments
     * @return
     */

//    public Set<String> addDepartmentIfNecessaryAndSortAscending(List<String> departments){
//
//        Set<String> result = new TreeSet<>();
//        String temp;
//        for(int i = 0; i < departments.size(); i++) {
//
//            if(departments.get(i).contains("\\")) {
//                temp = departments.get(i).substring(0, departments.get(i).lastIndexOf("\\"));
//                result.add(temp);
//            }
//            result.add(departments.get(i));
//        }
//        return result;
//    }

    public Set<Department> addDepartmentIfNecessaryAndSortAscendingVersionTwo(List<String> departments){

        String temp;
        for(int i = 0; i < departments.size(); i++) {

            if(departments.get(i).contains("\\")) {
                temp = departments.get(i).substring(0, departments.get(i).lastIndexOf("\\"));
                this.departmentSet.add(new Department(temp));
            }
            this.departmentSet.add(new Department(departments.get(i)));
        }

        return this.departmentSet;
    }


    public Set<Department> sortDepartmentsAscending(Set<Department> departments){

        Set<Department> result = new TreeSet<>(new DepartmentsComparatorAscending());
        result.addAll(departments);
        return result;
    }

    /**
     * Sorts departments descending
     * @param departments
     */
    public Set<Department> sortDepartmentDescending(Set<Department> departments){

        Set<Department> result = new TreeSet<>(new DepartmentsComparatorDescending());
        result.addAll(departments);
        return result;
    }
}
