package ru.mironenko.collectionslite.sortings;

import com.google.common.hash.HashCode;

import java.util.*;

/**
 * Created by nikita on 20.03.2017.
 */

//В классе SortUser из 1-го задания необходимо написать два метода:
//public List<User> sortHash (List<User>) - в этом методе необходимо определить Comparator для метода Collections.sort
//и отсортировать List<User> по hash-коду.
//public List<User> sortLength (List<User>) -
// в этом методе необходимо определить Comparator для метода Collections.sort
//и отсортировать List<User> по длине имени.
public class SortUser {

    /**
     * The method sort list of User by age
     * @param list
     * @return set of sorted users by age
     */
    public Set<User> sort (List<User> list) {
        Set<User> result = new TreeSet<>();

        for(User user : list) {
            result.add(user);
        }

        return result;
    }

    public List<User> sortLength (List<User> list) {

        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {

                Integer one = o1.getName().length();
                Integer two = o2.getName().length();

                return one.compareTo(two);
            }
        });

        return list;
    }

    public List<User> sortHash (List<User> list) {

        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {

                return o1.hashCode() - o2.hashCode();
            }
        });
        return list;
    }

}


