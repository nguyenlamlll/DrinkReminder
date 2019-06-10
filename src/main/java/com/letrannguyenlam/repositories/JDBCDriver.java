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
            try (Connection con = DriverManager.getConnection("jdbc:sqlserver://.;databaseName=DrinkReminder;integratedSecurity=True;", user, password)) {
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
//        Connection connection = DriverManager.getConnection("jdbc:sqlserver://EINSTEIN\\EINSTEIN;databaseName=DrinkReminder;integratedSecurity=True;", user, password);
        Connection connection = DriverManager.getConnection("jdbc:sqlserver://2cs-testing-server.database.windows.net:1433;database=2cs-website-staging;user=TestingAdmin@2cs-testing-server;password=SEhPN6ZMc7yehtF;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        return connection;
    }
}
