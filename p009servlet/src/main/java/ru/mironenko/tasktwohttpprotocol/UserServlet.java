package ru.mironenko.tasktwohttpprotocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//Создать модель User c полями name, login, email, createDate.
//Необходимо создать сервлет UsersServlet и определить там методы do Get Post Put Delete.
//Каждый метод сервлета должен выполнять только одно действие -
//создать пользователя, редактировать, получить. Удалить.
//Для хранения данных использовать базу данных Postgresql.
//То есть при старте сервлета вам нужно создать коннект к базе данных и записывать туда данные.

/**
 * Servlet class.
 */
public class UserServlet extends HttpServlet {

    /**
     * Instance of DBActions.
     */
    DBActions dbActions = new DBActions();


    /**
     * Retrieves user's info
     * @param req - request
     * @param resp - respond
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        User user = dbActions.getUser(userName, userLogin);

        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        writer.append(user.toString());
        writer.flush();
    }

    /**
     * Edits user's info
     * @param req - request
     * @param resp - respond
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        String newUserLogin = req.getParameter("newLogin");
        String newUserEmail = req.getParameter("newEmail");

        dbActions.editUser(userName, userLogin, newUserLogin, newUserEmail);
    }

    /**
     * Creates user
     * @param req - request
     * @param resp - respond
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        String userEmail = req.getParameter("email");
        dbActions.createUser(userName, userLogin, userEmail);
    }

    /**
     * Deletes user
     * @param req - request
     * @param resp - respond
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        dbActions.deleteUser(userName, userLogin);
    }

    /**
     * disconnects database.
     */
    @Override
    public void destroy() {
        dbActions.closeConnection();
    }
}
