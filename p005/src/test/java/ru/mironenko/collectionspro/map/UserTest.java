package ru.mironenko.collectionspro.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by nikita on 04.04.2017.
 */
public class UserTest {


    @Test
    public void mapTest() {
        User one = new User("Mike", 2, 30);
        User two = new User("Mike", 2, 30);

        Map<User, Object> map = new HashMap<>();
        map.put(one, "one");
        map.put(two, "two");

        System.out.println(map);
//        for(Map.Entry entry : map.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
//        }
    }

    @Test
    public void mapTestSet() {
        User one = new User("Mike", 2, 30);
        User two = new User("Mike", 2, 30);
        User three = two;

        Set<User> set = new HashSet<>();
        set.add(one);
        set.add(two);
        set.add(three);

        System.out.println(set);

    }
}