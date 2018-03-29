package ru.mironenka.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mironenka.control.DBController;
import ru.mironenka.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class UsersController extends HttpServlet{

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        DBController.getInstance().createUser(new User(req.getParameter("name"), req.getParameter("login"),
                req.getParameter("email"), req.getParameter("password"),  new Timestamp(new Date().getTime()),
                Integer.parseInt(req.getParameter("role_id"))));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = DBController.getInstance().getUsers();
        for(User tmp : users) {
            System.out.println(tmp);
        }

        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/AdminView.jsp").forward(req, resp);
    }
}


