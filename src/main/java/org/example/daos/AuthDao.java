package org.example.daos;

import org.example.models.LoginRequest;
import org.example.models.User;
import org.example.util.PasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDao {
    private PasswordEncoder passwordEncoder;

    public User getUser(final LoginRequest loginRequest) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT Username, Password, RoleId "
                    + "FROM `User` where Username = ? and Password = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, loginRequest.getUsername());
            statement.setString(2, loginRequest.getPassword());

            ResultSet resultSet = statement.executeQuery();

            if (PasswordEncoder.verifyPassword(loginRequest.getPassword(),
                    resultSet.getString("Password"))) {

                while (resultSet.next()) {
                    return new User(
                            resultSet.getString("Username"),
                            resultSet.getString("Password"),
                            resultSet.getInt("RoleID"));
                }
            }
        }
        return null;
    }
}
