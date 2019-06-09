package com.letrannguyenlam.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDriver {
    private static String db_url = "jdbc:sqlserver://.:;databaseName=DrinkReminder;";
    private static String user = "test_user";
    private static String password = "Test.123";

    public static void checkConnections() {
        try {
            try (Connection con = DriverManager.getConnection("jdbc:sqlserver://LENOVO-PC\\LAM;databaseName=DrinkReminder;integratedSecurity=True;", user, password)) {
                System.out.println("SQL Connection Established.");
            }
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/drinkreminder", "testingadmin", "Test.123")) {
                System.out.println("MySQL Connection Established.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection createSQLConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlserver://LENOVO-PC\\LAM;databaseName=DrinkReminder;integratedSecurity=True;", user, password);
        return connection;
    }
}
