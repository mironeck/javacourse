package ru.mironenko.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mironenko.control.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SinginController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(SinginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("WEB-INF/views/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        System.out.println(login + " " + password);

        boolean isCredential = UserStore.getInstance().isCredential(login, password);
            if (isCredential) {
                HttpSession session = req.getSession(false);
                session.setAttribute("login", login);
                if(login.equals("admin")) {
                    resp.sendRedirect(String.format("%s/", req.getContextPath()));
                } else {
                    req.setAttribute("user", UserStore.getInstance().getUser(login, password));
                    req.getRequestDispatcher("WEB-INF/views/SimpleUserView.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("error", "Credential invalid");
                doGet(req, resp);
            }

    }
}
