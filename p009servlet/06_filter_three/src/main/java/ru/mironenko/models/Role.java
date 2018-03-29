package ru.mironenko.models;

// Добавить механизм авторизации и аутентификации на фильтрах.
//         Добавить новую модель роль.
//         Каждый пользователь в системе имеет свою роль.
//         Предусмотреть. Роль - администратор. Он может добавить и редактировать любого пользователя в том числе себя.
//         В форме редактирования роли должен появиться список всех ролей.
//         Обычный пользователь может редактировать только себя. Причем он не может менять роль.
//         Важный момент. для servlet api 3.0 > синхронизацию делать не надо. В задаче так же объяснить почему.

public class Role {

    private String name;
    private int id;

    public Role(String name) {
        this.name = name;
    }

    public void setRole(String name) {
        this.name = name;
    }

    public String getRole() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
