package dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Интерфейс источника соединений с базой данных.
 */
public interface ConnectionBuilder {

    Connection getConnection() throws SQLException;
}
