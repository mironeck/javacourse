package ru.mironenko.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mironenko.control.DBActions;
import ru.mironenko.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class UsersController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        DBActions.getInstance().createUser(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), req.getParameter("password"),  new Timestamp(new Date().getTime())));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", DBActions.getInstance().getUserList());
        req.getRequestDispatcher("/WEB-INF/views/AdminView.jsp").forward(req, resp);
    }


}
