package ru.mironenko.collectionspro.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by nikita on 04.04.2017.
 */
public class UserHashCode {

    private String name;
    private int children;
    private int birthday;

    /**
     * Constructor
     * @param name
     * @param children
     * @param birthday
     */
    public UserHashCode(String name, int children, int birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * generate hashCode
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + birthday;
        return result;
    }


    public static void main(String[] args) {

        UserHashCode one = new UserHashCode("Mike", 2, 30);
        UserHashCode two = new UserHashCode("Mike", 2, 30);

        Map<UserHashCode, Object> map = new HashMap<>();
        map.put(one, "one");
        map.put(two, "two");

        System.out.println(map);

        Set<UserHashCode> set = new HashSet<>();
        set.add(one);
        set.add(two);

        System.out.println(set);
        System.out.println(one.hashCode());
        System.out.println(two.hashCode());
        System.out.println(one.equals(two));
    }
}
