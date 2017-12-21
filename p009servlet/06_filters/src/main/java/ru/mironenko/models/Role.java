package ru.mironenko.models;

// Добавить механизм авторизации и аутентификации на фильтрах.
//         Добавить новую модель роль.
//         Каждый пользователь в системе имеет свою роль.
//         Предусмотреть. Роль - администратор. Он может добавить и редактировать любого пользователя в том числе себя.
//         В форме редактирования роли должен появиться список всех ролей.
//         Обычный пользователь может редактировать только себя. Причем он не может менять роль.
//         Важный момент. для servlet api 3.0 > синхронизацию делать не надо. В задаче так же объяснить почему.

public class Role {

    public static final String ADMIN = "admin";
    public static final String USER = "user";

    private String role;

    public Role(String role) {
        this.role = role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
