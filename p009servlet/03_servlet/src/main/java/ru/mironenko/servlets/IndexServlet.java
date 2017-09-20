package ru.mironenko.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ru.mironenko.controls.DBActions;
import ru.mironenko.models.User;

public class IndexServlet extends HttpServlet {

    private final DBActions dbActions = new DBActions();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        List<User> users = this.dbActions.getUserList();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder builder = new StringBuilder("<table>");
        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>");

        if (!users.isEmpty()) {
            builder.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                    "User's name", "User's login", "User's email", "User's create date"));
            for (User user : users) {
                builder.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                        user.getName(), user.getLogin(), user.getEmail(), user.getCreateDate()));
            }
            writer.append("<p>All users:</p>" +
                    builder.toString());
        } else {
            builder.append(String.format("<tr>There is no user yet.</tr>"));
        }
        writer.append("</table>" +
                "<p>Create new user:</p>" +
                "<form action=" + req.getContextPath() + "/create method='post'>" +
                "User name : <input type=text name='name'>" +
                "User login : <input type=text name='login'>" +
                "User email : <input type=text name='email'>" +
                "<input type='submit'>" +
                "</form>" +
                "<br>" +
                "<p>Edit user:</p>" +
                "<form action=" + req.getContextPath() + "/edit method='post'>" +
                "User name : <input type=text name='name'>" +
                "User login : <input type=text name='login'>" +
                "User new user login : <input type=text name='newName'>" +
                "User new user email: <input type=text name='newLogin'>" +
                "<input type='submit'>" +
                "</form>" +
                "<br>" +
                "<p>Delete user:</p>" +
                "<form action=" + req.getContextPath() + "/delete method='post'>" +
                "User name : <input type=text name='name'>" +
                "User login : <input type=text name='login'>" +
                "<input type='submit'>" +
                "</form>" +
                "</body>" +
                "</html>");
        writer.flush();
    }

    /**
     * Handles post request.
     * @param req client's request instance.
     * @param resp response instance for client.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    public void destroy() {
        this.dbActions.closeConnection();
    }
}
