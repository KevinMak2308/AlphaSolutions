package gruppe5.alphasolutions.repositories;


import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

    /*public static Connection getConnection() {
        String url = null;
        String user = null;
        String password = null;
        Connection connection = null;
        if (connection != null) return connection;
        try (InputStream input = new ClassPathResource("application.properties").getInputStream()) {
            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException error) {
            error.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return connection;
    }*/


}

