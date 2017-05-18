package ru.mironenko.collectionspro.controltasks.extratask;

import java.util.*;

/**
 * Created by nikita on 17.05.2017.
 */
public class SortDepartment {

    private List<String> departments;

    /**
     * Getter of departments
     * @return List<E>
     */
    public List<String> getDepartments(){
        return this.departments;
    }

    /**
     * Adds department
     * @param departments
     * @return
     */
    public Set<String> addDepartmentIfNecessary(List<String> departments){

        Set<String> result = new TreeSet<>();
        String temp;
        for(int i = 0; i < departments.size(); i++) {

            if(departments.get(i).contains("\\")) {
                temp = departments.get(i).substring(0, departments.get(i).lastIndexOf("\\"));
                result.add(temp);
            }
            result.add(departments.get(i));
        }

        return result;
    }

    /**
     * По сути не нужный метод, т.к. в treeset данные уже сортируются в соответствии с natural sorting
     * @param departments
     * @return
     */
    public Set<String> sortDepartmentsAscending(Set<String> departments){

        return departments;
    }

    /**
     * Sorts departments descending
     * @param departments
     */
    public Set<String> sortDepartmentDescending(Set<String> departments){

        Set<String> result =  new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        result.addAll(departments);
        return result;
    }


    public static void main(String[] args) {

        List<String> departments = new ArrayList<>();
        departments.add("K1\\SK1");
        departments.add("K1\\SK2");
        departments.add("K1\\SK1\\SSK1");
        departments.add("K1\\SK1\\SSK2");
        departments.add("K2");
        departments.add("K2\\SK1\\SSK1");
        departments.add("K2\\SK1\\SSK2");


        SortDepartment sortDepartment = new SortDepartment();
        Set<String> primer = sortDepartment.addDepartmentIfNecessary(departments);
        for(String temp : primer) {
            System.out.println(temp);
        }

        System.out.println();
        Set<String> primerf = sortDepartment.sortDepartmentDescending(primer);

        for(String temp : primerf) {
            System.out.println(temp);
        }
    }
}
