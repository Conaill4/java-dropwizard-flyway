package org.example.daos;

import org.example.models.LoginRequest;
import org.example.models.User;
import org.example.util.PasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDao {

    public User getUser(final LoginRequest loginRequest) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query =
                    "SELECT Email, Password, "
                            + "RoleId FROM `User` WHERE Email = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            String username = loginRequest.getEmail();
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedHash = resultSet.getString("Password");
                if (PasswordEncoder.verifyPassword(loginRequest.getPassword(),
                        storedHash)) {
                    return new User(
                            resultSet.getString("Email"),
                            storedHash,
                            resultSet.getInt("RoleId"));

                }
            }
            return null;
        }
    }
}


