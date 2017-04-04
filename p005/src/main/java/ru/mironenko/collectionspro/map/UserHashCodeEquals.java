package ru.mironenko.collectionspro.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikita on 04.04.2017.
 */
public class UserHashCodeEquals {

    private String name;
    private int children;
    private int birthday;

    /**
     * Constructor
     * @param name
     * @param children
     * @param birthday
     */
    public UserHashCodeEquals(String name, int children, int birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserHashCodeEquals)) return false;

        UserHashCodeEquals that = (UserHashCodeEquals) o;

        if (children != that.children) return false;
        if (birthday != that.birthday) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + birthday;
        return result;
    }

    public static void main(String[] args) {

        UserHashCodeEquals one = new UserHashCodeEquals("Mike", 2, 30);
        UserHashCodeEquals two = new UserHashCodeEquals("Mike", 2, 30);

        Map<UserHashCodeEquals, Object> map = new HashMap<>();
        map.put(one, "one");
        map.put(two, "two");

        System.out.println(map);
        System.out.println(one.hashCode());
        System.out.println(two.hashCode());
        System.out.println(one.equals(two));
    }
}
