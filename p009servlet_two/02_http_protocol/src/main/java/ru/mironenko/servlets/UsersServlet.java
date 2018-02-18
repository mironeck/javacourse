package ru.mironenko.servlets;

import ru.mironenko.Control.UserStore;
import ru.mironenko.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//Создать модель User c полями name, login, email, createDate.
//        Необходимо создать сервлет UsersServlet и определить там методы do Get Post Put Delete.
//        Каждый метод сервлета должен выполнять только одно действие - создать пользователя, редактировать, получить. Удалить.
//        Тестирование сервлета осуществялять через Test RESTFull service


public class UsersServlet extends HttpServlet {

    private final UserStore userStore = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        User user = userStore.getUser(userName, userLogin);

        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(user.toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        String newUserLogin = req.getParameter("newLogin");
        String newUserEmail = req.getParameter("newEmail");

        userStore.editUser(userName, userLogin, newUserLogin, newUserEmail);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        String userEmail = req.getParameter("email");
        userStore.createUser(userName, userLogin, userEmail);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        userStore.deleteUser(userName, userLogin);
    }

}
