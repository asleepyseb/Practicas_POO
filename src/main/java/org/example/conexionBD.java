package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=practicas_db;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PSWD = "UTTProy67";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PSWD);
    }
}
