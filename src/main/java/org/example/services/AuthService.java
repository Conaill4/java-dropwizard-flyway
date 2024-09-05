package org.example.services;

import io.jsonwebtoken.Jwts;
import org.example.AuthValidator;
import org.example.daos.AuthDao;
import org.example.exceptions.EmailException;
import org.example.exceptions.InvalidException;
import org.example.exceptions.PasswordException;
import org.example.models.LoginRequest;
import org.example.models.User;

import java.security.Key;
import java.sql.Date;
import java.sql.SQLException;

public class AuthService {
    private final AuthDao authDao;
    private final Key key;
    private final AuthValidator authValidator;
    final int expiration = 28800000;

    public AuthService(final AuthDao authDao, final Key key,
                       final AuthValidator authValidator) {
        this.authDao = authDao;
        this.key = key;
        this.authValidator = authValidator;
    }

    public String login(final LoginRequest loginRequest)
            throws SQLException, EmailException, PasswordException {

        User user = authDao.getUser(loginRequest);

        authValidator.isLoginValid(loginRequest);

        return generateJwtToken(user);
    }

    private String generateJwtToken(final User user) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .claim("Role", user.getRoleId())
                .subject(user.getEmail())
                .issuer("java-dropwizard-flyway")
                .signWith(key)
                .compact();
    }
}
