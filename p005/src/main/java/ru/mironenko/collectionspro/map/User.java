package ru.mironenko.collectionspro.map;

/**
 * Created by nikita on 04.04.2017.
 */
//Создать модель User и три поля String name, int children, Calendar birthday,

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

}
