package ru.mironenko.servlets;

import org.junit.Test;
import ru.mironenko.control.UserStore;
import ru.mironenko.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CreateUserTest {


    @Test
    public void addUser() throws ServletException, IOException {

        UserStore userStore = UserStore.getInstance();
        CreateUserServlet createUserServlet = new CreateUserServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);


        createUserServlet.doPost(request, response);

        List<User> userList = userStore.getUserList();


    }

}