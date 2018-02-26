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

public class UsersController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        synchronized (session) {
            if(session == null || req.getAttribute("login") == null) {
                resp.sendRedirect(String.format("%s/signin", req.getContextPath()));
            } else {
                req.setAttribute("users", UserStore.getInstance().getUserList());
                req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        UserStore.getInstance().closeConnection();
    }
}
