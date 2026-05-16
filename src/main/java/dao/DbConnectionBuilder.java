package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Построитель соединения с PostgreSQL на основе параметров конфигурационного файла.
 *
 * @author Демидко М. Д., группа ПИZ-331
 */
public class DbConnectionBuilder implements ConnectionBuilder {

    public DbConnectionBuilder() {
        try {
            Class.forName(ConnectionProperty.getProperty("db.driver.class"));
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("JDBC driver not found", ex);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = ConnectionProperty.getProperty("db.url");
        String login = ConnectionProperty.getProperty("db.login");
        String password = ConnectionProperty.getProperty("db.password");
        return DriverManager.getConnection(url, login, password);
    }
}
