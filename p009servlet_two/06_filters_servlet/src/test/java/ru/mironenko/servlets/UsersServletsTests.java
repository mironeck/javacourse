package ru.mironenko.servlets;

import org.junit.Test;
import ru.mironenko.control.UserStore;
import ru.mironenko.models.Role;
import ru.mironenko.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServletsTests {


    @Test
    public void whenCreateUserThanShouldGetUser() throws ServletException, IOException {

        UserStore userStore = UserStore.getInstance();

        CreateUserServlet createUserServlet = new CreateUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("test");
        when(request.getParameter("email")).thenReturn("test");
        when(request.getParameter("role")).thenReturn("1");

        createUserServlet.doPost(request, response);

        assertThat(userStore.getUser("test", "test").getName(), is("test"));
    }

    @Test
    public void whenEditUserThanShouldReturnEditedUser() throws ServletException, IOException {

        UserStore userStore = UserStore.getInstance();

        EditUserServlet editUserServlet = new EditUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("newLogin")).thenReturn("testTwo");
        when(request.getParameter("newEmail")).thenReturn("testTwo");

        editUserServlet.doPost(request, response);

        assertThat(userStore.getUser("testTwo", "test").getLogin(), is("testTwo"));
    }

    @Test
    public void whenDeleteUserThanShouldReturnNull() throws ServletException, IOException {

        UserStore userStore = UserStore.getInstance();

        DeleteUserServlet deleteUserServlet = new DeleteUserServlet();

        User user = new User("delete", "delete", "delete", "delete", new Timestamp(new Date().getTime()));
        Role role = new Role(1, "usual user");
        user.setRole(role);

        userStore.createUser(user);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("name")).thenReturn("delete");
        when(request.getParameter("login")).thenReturn("delete");

        deleteUserServlet.doPost(request, response);

        assertNull(userStore.getUser("delete", "delete"));
    }
}