package ru.mironenko.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mironenko.control.UserStore;
import ru.mironenko.models.Role;
import ru.mironenko.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class CreateUserServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(CreateUserServlet.class);

    private final UserStore userStore = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        String userPass = req.getParameter("password");
        String userEmail = req.getParameter("email");
        int roleId = Integer.parseInt(req.getParameter("role"));
        User user = new User(userName, userLogin, userPass, userEmail, new Timestamp(new Date().getTime()));
        Role role = new Role(roleId, "usual user");
        user.setRole(role);
        userStore.createUser(user);

        resp.sendRedirect(String.format("%s/", req.getContextPath()));

    }


    @Override
    public void destroy() {
        userStore.closeConnection();
    }
}
