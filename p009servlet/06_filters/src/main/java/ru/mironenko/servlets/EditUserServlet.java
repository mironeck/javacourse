package ru.mironenko.servlets;

import ru.mironenko.control.DBActions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/EditUserView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);

            String email = req.getParameter("name");
            String newLogin = req.getParameter("newLogin");
            String newEmail = req.getParameter("newEmail");
            int newRoleId = Integer.parseInt(req.getParameter("newRoleId"));

        DBActions.getInstance().editUser(email, newLogin, newEmail, newRoleId);
    }
}
