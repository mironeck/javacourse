package ru.mironenko.jdbc.lessonexamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


/**
 * Created by nikita on 01.07.2017.
 */
public class SQLStrorage {

    private static final Logger log = LoggerFactory.getLogger(SQLStrorage.class);

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/java_from_a_to_z";
        String username = "postgres";
        String password = "4istyebotinki";
        Connection conn = null;
        try {

            //добавление новой строки и вывод id нового пользователя
            conn = DriverManager.getConnection(url, username, password);
//            PreparedStatement st = conn.prepareStatement("insert into users(login, password, create_date) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//            st.setString(1, "new java user test2");
//            st.setString(2, "password");
//            st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
//            st.executeUpdate();
//            ResultSet generatedKeys = st.getGeneratedKeys();
//            if(generatedKeys.next()) {
//                System.out.println(generatedKeys.getInt(1));
//            }

            //обновление данных
//            PreparedStatement st = conn.prepareStatement("update users set login = ? where id = ?");
//            st.setString(1, "new java user test 3");
//            st.setInt(2, 10);
//            st.executeUpdate();

            //удаление данных
            //обновление данных
            PreparedStatement st = conn.prepareStatement("delete from users where id = ?");
            st.setInt(1, 11);
            st.executeUpdate();
            //вывод определённых строк
//            PreparedStatement st = conn.prepareStatement("SELECT * FROM users as u where u.id in (?, ?, ?)");
//            st.setInt(1, 3);
//            st.setInt(2, 10);
//            st.setInt(3, 11);
//
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                System.out.println(String.format("%s %s", rs.getString("login"), rs.getTimestamp("create_date")));
//            }
//            rs.close();
//            st.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

    }
}
