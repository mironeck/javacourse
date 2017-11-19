package ru.mironenko.servlets;

import ru.mironenko.controls.DBActions;
import ru.mironenko.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteUserServlet extends HttpServlet {

    DBActions dbActions = DBActions.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");

        dbActions.deleteUser(userName, userLogin);
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

    @Override
    public void destroy() {
        this.dbActions.closeConnection();
    }
}
