package ru.mironenko.servlets;

import ru.mironenko.control.DBActions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SigninController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if(DBActions.getInstance().isCredential(login, password)) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("login", login);
                if(DBActions.getInstance().getRoleId(login) == 1) {
                    resp.sendRedirect("/WEB-INF/views/AdminView.jsp");
                } else if(DBActions.getInstance().getRoleId(login) == 2){
                    resp.sendRedirect("/WEB-INF/views/UserView.jsp");
                }
            }
        } else {
            req.setAttribute("error", "Credential invalid");
            doGet(req, resp);
        }
    }
}
