package ru.mironenko.collectionspro.map;

/**
 * Created by nikita on 04.04.2017.
 */
//Создать модель User и три поля String name, int children, Calendar birthday,

import java.util.HashMap;
import java.util.Map;

/**
 * Class User
 */
public class User {

    private String name;
    private int children;
    private int birthday;

    /**
     * Constructor
     * @param name
     * @param children
     * @param birthday
     */
    public User(String name, int children, int birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {

        User one = new User("Mike", 2, 30);
        User two = new User("Mike", 2, 30);

        Map<User, Object> map = new HashMap<>();
        map.put(one, "one");
        map.put(two, "two");

        System.out.println(map);
    }

}
