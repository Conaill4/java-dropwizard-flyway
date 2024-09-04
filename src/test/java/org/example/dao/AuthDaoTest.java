package org.example.dao;

import org.example.daos.AuthDao;
import org.example.models.JobRole;
import org.example.models.LoginRequest;
import org.example.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthDaoTest {

    private final AuthDao authDao = new AuthDao();

    LoginRequest loginRequest = new LoginRequest(
            "admin@kainos.com",
            "admin"
    );

    User user = new User(
            "admin@kainos.com",
            "$2a$10$abwOc0Pn.kTEmWCa7GJ0ROXGwmwJXFzX6Fh.81fmS4zOZdjj81jzW",
            1
    );


    @Test
    void getUser_DAOShouldReturnUser()
            throws SQLException {

        User actualUser = authDao.getUser(loginRequest);
        assertEquals(user.getEmail(), actualUser.getEmail());
        assertEquals(user.getPassword(), actualUser.getPassword());
        assertEquals(user.getRoleId(), actualUser.getRoleId());
    }
}
