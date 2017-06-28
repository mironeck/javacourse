package ru.mironenko.collectionspro.controltasks.extratask;

import java.util.*;

/**
 * Created by nikita on 17.05.2017.
 */
public class SortDepartment {

    Department department = new Department();

    private Set<Department> departmentSet = new TreeSet<>();

    public Set<Department> getDepartmentSet() {
        return this.departmentSet;
    }

    /**
     * Adds departments and sorts it in natural sorting in set
     * @param departments
     * @return
     */

    public Department addDepartmentIfNecessary(List<String> departments){

        String temp;
        for(int i = 0; i < departments.size(); i++) {

            if(departments.get(i).contains("\\")) {
                temp = departments.get(i).substring(0, departments.get(i).lastIndexOf("\\"));
                this.department.getDepartments().add(temp);
            }
            this.department.getDepartments().add(departments.get(i));
        }

        return this.department;
    }


    public Set<String> sortDepartmentsAscending(Department department){

        Set<String> result = new TreeSet<String>(new DepartmentsComparatorAscending());
        result.addAll(department.getDepartments());
        return result;
    }

    /**
     * Sorts departments descending
     * @param department
     */
    public Set<String> sortDepartmentDescending(Department department){

        Set<String> result = new TreeSet<>(new DepartmentsComparatorDescending());
        result.addAll(department.getDepartments());
        return result;
    }

    public static void main(String[] args) {

        SortDepartment sortDepartment = new SortDepartment();
        List<String> departments = new ArrayList<>();

        departments.add("K1\\SK1");
        departments.add("K1\\SK2");
        departments.add("K1\\SK1\\SSK1");
        departments.add("K1\\SK1\\SSK2");
        departments.add("K2");
        departments.add("K2\\SK1\\SSK1");
        departments.add("K2\\SK1\\SSK2");

        Department department = sortDepartment.addDepartmentIfNecessary(departments);

        Set<String> ascending = sortDepartment.sortDepartmentsAscending(department);

//        for(String tmp : ascending) {
//            System.out.println(tmp);
//        }


        System.out.println();

        Set<String> descending = sortDepartment.sortDepartmentDescending(department);
        for(String tmp : descending) {
            System.out.println(tmp);
        }
    }
}
