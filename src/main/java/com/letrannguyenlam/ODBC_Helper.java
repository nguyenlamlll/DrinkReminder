package com.letrannguyenlam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ODBC_Helper {
    private static String db_url = "jdbc:sqlserver://CONAN\\LON:;databaseName=DrinkReminder;user=sa;password=Test.123";
    private static String user = "sa";
    private static String pw = "Test.123";
    public static void connect() {
        try {
//            try (Connection con = DriverManager.getConnection("jdbc:sqlserver://.;databaseName=DrinkReminder")){
//                System.out.println("SQL Connection Established.");
//            }
            try(Connection connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/drinkreminder", "testingadmin", "Test.123")) {
                System.out.println("MySQL Connection Established.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
