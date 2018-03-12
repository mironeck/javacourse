package ru.mironenko.models;

// Добавить механизм авторизации и аутентификации на фильтрах.
//         Добавить новую модель роль.
//         Каждый пользователь в системе имеет свою роль.
//         Предусмотреть. Роль - администратор. Он может добавить и редактировать любого пользователя в том числе себя.
//         В форме редактирования роли должен появиться список всех ролей.
//         Обычный пользователь может редактировать только себя. Причем он не может менять роль.
//         Важный момент. для servlet api 3.0 > синхронизацию делать не надо. В задаче так же объяснить почему.

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Role {

    private static final Logger LOG = LoggerFactory.getLogger(Role.class);

    private final int id;
    private final String name;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Role {" +
                " id= " + id +
                ", name = '" + name + '\'' +
                '}';
    }
}
