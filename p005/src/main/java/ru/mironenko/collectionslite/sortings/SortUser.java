package ru.mironenko.collectionslite.sortings;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by nikita on 20.03.2017.
 */
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

}


