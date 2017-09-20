package ru.mironenko.servlets;

import ru.mironenko.controls.DBActions;
import ru.mironenko.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EditUserServlet extends HttpServlet{

    private final DBActions dbActions = new DBActions();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        String newUserLogin = req.getParameter("newLogin");
        String newUserEmail = req.getParameter("newEmail");

        User user = dbActions.getUser(userName, userLogin);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>");
        if (user != null) {
            this.dbActions.editUser(userName, userLogin, newUserLogin, newUserEmail);
            writer.append(String.format("<p>User %s is now </p><p>%s</p>.", user.toString(),
                    this.dbActions.getUser(userName, newUserLogin)));
        } else {
            writer.append(String.format("<p>There is no user with name %s and login %s.</p>",  userName, userLogin));
        }
        writer.append("<p><a href=" + req.getContextPath() + "/index method='get'>Go to main page.</a></p>" +
                "</body>" +
                "</html>");
        writer.flush();
    }

    @Override
    public void destroy() {
        this.dbActions.closeConnection();
    }
}
