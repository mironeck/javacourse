package ru.mironenko.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mironenko.control.DBActions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        DBActions.getInstance().createUser(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), req.getParameter("password"), req.getParameter("role"));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", DBActions.getInstance().getUserList());
        req.getRequestDispatcher("/WEB-INF/views/AdminView.jsp").forward(req, resp);
    }


}
