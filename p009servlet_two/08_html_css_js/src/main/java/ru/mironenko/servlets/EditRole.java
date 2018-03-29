package ru.mironenko.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mironenko.control.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditRole extends HttpServlet {

    private final static Logger LOG = LoggerFactory.getLogger(EditRole.class);

    private final UserStore userStore = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("roles", userStore.getRoles());
        req.getRequestDispatcher("/WEB-INF/views/EditRole.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        int roleId =  Integer.parseInt(req.getParameter("role_id"));
        String name = req.getParameter("name");
        int newRoleId =  Integer.parseInt(req.getParameter("roleNewId"));
        String newName = req.getParameter("newName");

        userStore.editRole(roleId, name, newRoleId, newName);
        doGet(req, resp);
    }
}
