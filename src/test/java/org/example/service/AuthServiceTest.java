package org.example.service;

import io.jsonwebtoken.security.Keys;
import org.example.daos.AuthDao;
import org.example.exceptions.InvalidException;
import org.example.models.LoginRequest;
import org.example.models.User;
import org.example.services.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.Key;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    AuthDao authDao = Mockito.mock(AuthDao.class);
    Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
    AuthService authService = new AuthService(authDao, key);

    LoginRequest loginRequest = new LoginRequest(
            "admin@kainos.com",
            "admin"
    );

    @Test
    void login_ShouldReturnJwtToken_WhenCredentialsAreValid() throws SQLException, InvalidException {
        User validUser = new User("admin@kainos.com", "admin", 1);

        Mockito.when(authDao.getUser(loginRequest)).thenReturn(validUser);

        String jwtToken = authService.login(loginRequest);

        assertNotNull(jwtToken);
    }

    @Test
    void login_ShouldThrowInvalidException_WhenCredentialsAreInvalid() throws SQLException, InvalidException {

        Mockito.when(authDao.getUser(loginRequest)).thenReturn(null);

        assertThrows(InvalidException.class, () -> authService.login(loginRequest));
    }
}
