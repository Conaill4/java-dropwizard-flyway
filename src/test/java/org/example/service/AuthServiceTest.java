package org.example.service;

import io.jsonwebtoken.security.Keys;
import org.example.daos.AuthDao;
import org.example.exceptions.EmailException;
import org.example.exceptions.PasswordException;
import org.example.models.LoginRequest;
import org.example.models.User;
import org.example.services.AuthService;
import org.example.validators.AuthValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.Key;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    AuthDao authDao = Mockito.mock(AuthDao.class);
    Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
    AuthValidator authValidator = Mockito.mock(AuthValidator.class);
    AuthService authService = new AuthService(authDao, key, authValidator);

    LoginRequest loginRequest = new LoginRequest(
            "admin@kainos.com",
            "Adm1n$"
    );

    @Test
    public void login_ShouldReturnJwtToken_WhenCredentialsAreValid() throws SQLException,
            EmailException, PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "admin@kainos.com",
                "Adm1n$"
        );

        User validUser = new User("admin@kainos.com", "Adm1n$", 1);

        Mockito.when(authDao.getUser(loginRequest)).thenReturn(validUser);

        String jwtToken = authService.login(loginRequest);

        assertNotNull(jwtToken);
    }

    @Test
    public void login_ShouldThrowEmailException_WhenEmailIsNull() throws EmailException,
            PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "",
                "Adm1n$"
        );

        Mockito.when(authValidator.isLoginValid(loginRequest))
                .thenThrow(EmailException.class);

        assertThrows(EmailException.class, () -> authService.login(loginRequest));
    }
    @Test
    public void login_ShouldThrowEmailException_WhenEmailIsInvalid() throws
            EmailException, PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "admin&@kainos.com",
                "Adm1n$"
        );

        Mockito.when(authValidator.isLoginValid(loginRequest))
                .thenThrow(EmailException.class);

        assertThrows(EmailException.class, () -> authService.login(loginRequest));
    }
    @Test
    public void login_ShouldThrowEmailException_WhenEmailIsHasExtraAt() throws
            EmailException, PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "admin@@kainos.com",
                "Adm1n$"
        );

        Mockito.when(authValidator.isLoginValid(loginRequest))
                .thenThrow(EmailException.class);

        assertThrows(EmailException.class, () -> authService.login(loginRequest));
    }
    @Test
    public void login_ShouldThrowEmailException_WhenEmailIsHasNoAt() throws
            EmailException, PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "adminkainos.com",
                "Adm1n$"
        );

        Mockito.when(authValidator.isLoginValid(loginRequest))
                .thenThrow(EmailException.class);

        assertThrows(EmailException.class, () -> authService.login(loginRequest));
    }
    @Test
    public void login_ShouldThrowEmailException_WhenEmailIsHasAtInTheBeginning() throws
            EmailException, PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "@adminkainos.com",
                "Adm1n$"
        );

        Mockito.when(authValidator.isLoginValid(loginRequest))
                .thenThrow(EmailException.class);

        assertThrows(EmailException.class, () -> authService.login(loginRequest));
    }
    @Test
    public void login_ShouldThrowEmailException_WhenEmailIsHasASpace() throws
            EmailException, PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "admin @kainos.com",
                "Adm1n$"
        );

        Mockito.when(authValidator.isLoginValid(loginRequest))
                .thenThrow(EmailException.class);

        assertThrows(EmailException.class, () -> authService.login(loginRequest));
    }
    @Test
    public void login_ShouldThrowEmailException_WhenEmailIsHasNoDomain() throws
            EmailException, PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "admin@",
                "Adm1n$"
        );

        Mockito.when(authValidator.isLoginValid(loginRequest))
                .thenThrow(EmailException.class);

        assertThrows(EmailException.class, () -> authService.login(loginRequest));
    }
    @Test
    public void login_ShouldThrowPasswordException_WhenPasswordIsNull() throws
            EmailException, PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "admin@kainos.com",
                ""
        );

        Mockito.when(authValidator.isLoginValid(loginRequest))
                .thenThrow(PasswordException.class);

        assertThrows(PasswordException.class, () -> authService.login(loginRequest));
    }
    @Test
    public void login_ShouldThrowPasswordException_WhenPasswordIsInvalid() throws
            EmailException, PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "admin@kainos.com",
                "admin"
        );

        Mockito.when(authValidator.isLoginValid(loginRequest))
                .thenThrow(PasswordException.class);

        assertThrows(PasswordException.class, () -> authService.login(loginRequest));
    }
    @Test
    public void login_ShouldThrowPasswordException_WhenPasswordDoesNotHaveACaptial() throws
            EmailException, PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "admin@kainos.com",
                "adm1n$"
        );

        Mockito.when(authValidator.isLoginValid(loginRequest))
                .thenThrow(PasswordException.class);

        assertThrows(PasswordException.class, () -> authService.login(loginRequest));
    }
    @Test
    public void login_ShouldThrowPasswordException_WhenPasswordDoesNotHaveANumber() throws
            EmailException, PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "admin@kainos.com",
                "Admin$"
        );

        Mockito.when(authValidator.isLoginValid(loginRequest))
                .thenThrow(PasswordException.class);

        assertThrows(PasswordException.class, () -> authService.login(loginRequest));
    }
    @Test
    public void login_ShouldThrowPasswordException_WhenPasswordDoesNotHaveASpecialCharacter() throws
            EmailException, PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "admin@kainos.com",
                "Adm1n"
        );

        Mockito.when(authValidator.isLoginValid(loginRequest))
                .thenThrow(PasswordException.class);

        assertThrows(PasswordException.class, () -> authService.login(loginRequest));
    }
    @Test
    public void login_ShouldThrowPasswordException_WhenPasswordDoesNotHaveALowerCase() throws
            EmailException, PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "admin@kainos.com",
                "ADM1N$"
        );

        Mockito.when(authValidator.isLoginValid(loginRequest))
                .thenThrow(PasswordException.class);

        assertThrows(PasswordException.class, () -> authService.login(loginRequest));
    }
    @Test
    public void login_ShouldThrowPasswordException_WhenPasswordDoesHaveASpace() throws
            EmailException, PasswordException {
        LoginRequest loginRequest = new LoginRequest(
                "admin@kainos.com",
                "Adm 1n$"
        );

        Mockito.when(authValidator.isLoginValid(loginRequest))
                .thenThrow(PasswordException.class);

        assertThrows(PasswordException.class, () -> authService.login(loginRequest));
    }
}
