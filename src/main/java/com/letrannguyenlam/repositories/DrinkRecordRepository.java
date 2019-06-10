package com.letrannguyenlam.repositories;

import com.letrannguyenlam.repositories.models.DrinkRecord;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DrinkRecordRepository {
    private JDBCDriver database;

    public DrinkRecordRepository() {
        database = new JDBCDriver();
    }

    public LinkedList<DrinkRecord> getAllDrinkRecords() {
        try (Connection connection = database.createSQLConnection()) {
            String query = "SELECT * FROM DrinkRecord";
            LinkedList<DrinkRecord> drinkRecords = new LinkedList<DrinkRecord>();
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    DrinkRecord record = new DrinkRecord();
                    record.setId(resultSet.getInt("Id"));
                    record.setAmount(resultSet.getDouble("Amount"));
                    record.setTimeTaken(resultSet.getTimestamp("TimeTaken"));
                    record.setUserId(resultSet.getInt("UserId"));

                    drinkRecords.add(record);
                }
            }
            return drinkRecords;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    public LinkedList<DrinkRecord> getDrinkRecords(int userId) {
        try (Connection connection = database.createSQLConnection()) {
            String query = "SELECT * FROM DrinkRecord WHERE UserId = ?";
            LinkedList<DrinkRecord> drinkRecords = new LinkedList<DrinkRecord>();
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    DrinkRecord record = new DrinkRecord(
                            resultSet.getInt("Id"),
                            resultSet.getInt("UserId"),
                            resultSet.getDouble("Amount"),
                            resultSet.getTimestamp("TimeTaken")
                    );
                    drinkRecords.add(record);
                }
            }
            return drinkRecords;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    public double getAmountByMonth(int userId, int month) {
        try (Connection connection = database.createSQLConnection()) {
            String query = "SELECT UserId, SUM(sumAmountByDate) AS AmountByMonth FROM (" +
                    "SELECT UserId, TimeTaken, SUM(Amount) AS sumAmountByDate " +
                    "FROM dbo.DrinkRecord " +
                    "WHERE userId = ? " +
                    "GROUP BY UserId, TimeTaken " +
                    "HAVING DATEPART(MONTH, TimeTaken) = ?) AS AmountByDate " +
                    "GROUP BY AmountByDate.UserId";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                statement.setInt(2, month);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    return resultSet.getDouble("AmountByMonth");
                }

            }
            return 0.0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void createDrinkRecord(DrinkRecord record) {
        try (Connection connection = database.createSQLConnection()) {
            String query = "INSERT INTO DrinkRecord (UserId, Amount, TimeTaken)" +
                    "VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, record.getUserId());
                statement.setDouble(2, record.getAmount());
                statement.setTimestamp(3, record.getTimeTaken());

                int returnToken = statement.executeUpdate();
                int i = 2;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }


}
