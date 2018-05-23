package ru.mironenko.servlets;

import com.google.gson.Gson;
import ru.mironenko.control.UserStore;
import ru.mironenko.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class JsonController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserStore.getInstance().getUserList();
        String json = new Gson().toJson(users);
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User(req.getParameter("name"),
                req.getParameter("login"), req.getParameter("password"), req.getParameter("email"),
                new Timestamp(new Date().getTime()));
        user.setCountry(req.getParameter("country"));
        user.setCity(req.getParameter("city"));
        System.out.println(user);
        UserStore.getInstance().createUser(user);
    }
}
