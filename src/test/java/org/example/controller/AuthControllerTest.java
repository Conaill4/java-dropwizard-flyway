package org.example.controller;

import org.example.controllers.AuthController;
import org.example.exceptions.EmailException;
import org.example.exceptions.InvalidException;
import org.example.exceptions.PasswordException;
import org.example.models.LoginRequest;
import org.example.services.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthControllerTest {
    AuthService authService = Mockito.mock(AuthService.class);
    AuthController authController = new AuthController(authService);

    LoginRequest loginRequest = new LoginRequest(
            "admin@kainos.com",
            "Adm1n$"
    );

    @Test
    void successfulLogin_shouldReturn200() throws SQLException, EmailException,
            PasswordException {
        String token = "123";
        Mockito.when(authService.login(loginRequest)).thenReturn(token);

        Response response = authController.login(loginRequest);

        assertEquals(200, response.getStatus());
    }

    @Test
    void Login_shouldReturn500IfSQLError() throws SQLException, EmailException,
            PasswordException {

        Mockito.when(authService.login(loginRequest)).thenThrow(SQLException.class);

        Response response = authController.login(loginRequest);

        assertEquals(500, response.getStatus());
    }

    @Test
    void Login_shouldReturn400IfBadRequest() throws SQLException, EmailException,
            PasswordException {

        Mockito.when(authService.login(loginRequest)).thenThrow(EmailException.class);

        Response response = authController.login(loginRequest);

        assertEquals(400, response.getStatus());
    }
}
