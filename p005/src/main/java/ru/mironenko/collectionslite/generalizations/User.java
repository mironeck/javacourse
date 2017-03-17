package ru.mironenko.collectionslite.generalizations;

/**
 * Created by nikita on 17.03.2017.
 */

//Создать класс User с полями id, name, city. Cоздать клаcc UserConvert.
//В классе UserConvert написать метод public HashMap<Integer, User> process(List<User> list) {},
// который принимает в себя список пользователей и конвертирует его в Map с ключом Integer id и
// соответствующим ему User.


public class User {


    private int id;
    private String name;
    private String city;

    public User (int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }
}
