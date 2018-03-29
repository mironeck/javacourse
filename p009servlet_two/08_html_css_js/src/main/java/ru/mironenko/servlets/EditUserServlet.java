package ru.mironenko.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mironenko.control.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserServlet extends HttpServlet {

    private final UserStore userStore = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        String newUserLogin = req.getParameter("newLogin");
        String newUserEmail = req.getParameter("newEmail");

        userStore.editUser(userName, userLogin, newUserLogin, newUserEmail);
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

    @Override
    public void destroy() {
        userStore.closeConnection();
    }
}
