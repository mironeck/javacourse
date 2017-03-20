package ru.mironenko.collectionslite.sortings;

/**
 * Created by nikita on 20.03.2017.
 */

// Необходимо создать модель User с полями name, age.
// Класс User должен реализовать интерфейс Comparable.
// В классе SortUser написать метод public Set<User> sort (List<User>),
// который будет возвращать TreeSet пользователей, отсортированных по возрасту в порядке возрастания.

public class User implements Comparable<User>{

    private String name;
    private Integer age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(User user) {
        return age.compareTo(user.getAge());
    }

}
