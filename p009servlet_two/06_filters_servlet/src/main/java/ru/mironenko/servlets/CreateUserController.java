package ru.mironenko.servlets;

import ru.mironenko.control.UserStore;
import ru.mironenko.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class CreateUserController extends HttpServlet{


    private final UserStore userStore = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        String userPass = req.getParameter("password");
        String userEmail = req.getParameter("email");
        int roleId = Integer.parseInt(req.getParameter("role_id"));
        User user = new User(userName, userLogin, userPass, userEmail, roleId, new Timestamp(new Date().getTime()));
        userStore.createUser(user);

        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

    @Override
    public void destroy() {
        userStore.closeConnection();
    }
}
