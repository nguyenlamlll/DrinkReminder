package com.letrannguyenlam.repositories;

import com.letrannguyenlam.repositories.models.DrinkRecord;
import com.letrannguyenlam.repositories.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class UserRepository {
    private JDBCDriver database;

    public UserRepository() {
        database = new JDBCDriver();
    }


    public void createUser(User user) {
        try (Connection connection = database.createSQLConnection()) {
            String query = "INSERT INTO [User] (Username, Password, Name, DateOfBirth, Weight) " +
                    "VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getName());
                statement.setDate(4, user.getDateOfBirth());
                statement.setDouble(5, user.getWeight());

                int returnToken = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void updateUser(User user) {
        // The updater may not know the Id of the user.
        var updatingUser = this.getUser(user.getUsername());
        user.setId(updatingUser.getId());

        try (Connection connection = database.createSQLConnection()) {
            String query = "UPDATE [User] " +
                    "SET Username = ?, Password = ?, Name = ?, DateOfBirth = ?, Weight = ? " +
                    "WHERE Id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getName());
                statement.setDate(4, user.getDateOfBirth());
                statement.setDouble(5, user.getWeight());
                statement.setInt(6, user.getId());

                int returnToken = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    public User getUser(String username) {
        try (Connection connection = database.createSQLConnection()) {
            String query = "SELECT * FROM [User] WHERE Username = ?";
            LinkedList<User> users = new LinkedList<User>();
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    User user = new User(
                            resultSet.getInt("Id"),
                            resultSet.getString("Username"),
                            resultSet.getString("Password"),
                            resultSet.getString("Name"),
                            resultSet.getDate("DateOfBirth"),
                            resultSet.getDouble("Weight")
                            );
                    users.add(user);
                }
            }
            return users.getFirst();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }
}
