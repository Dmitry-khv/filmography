package ru.konoplev;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@DisplayName("Тест должен")
class DBConnectionTest {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "12344321";
    private Connection connection;

    @Test
    @DisplayName("Получать connection")
    void shouldGetConnectionToDB() throws SQLException {
        connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}