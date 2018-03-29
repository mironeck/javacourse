package ru.mironenko.servlets;

import ru.mironenko.control.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserServletForSimpleUser extends HttpServlet {

    private final UserStore userStore = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        String userPassword = req.getParameter("password");

        String newUserLogin = req.getParameter("newLogin");
        String newUserEmail = req.getParameter("newEmail");

        userStore.editUser(userName, userLogin, newUserLogin, newUserEmail);
        req.setAttribute("user", UserStore.getInstance().getUser(newUserLogin, userPassword));
        req.getRequestDispatcher("WEB-INF/views/SimpleUserView.jsp").forward(req, resp);

    }


    @Override
    public void destroy() {
        userStore.closeConnection();
    }

}
