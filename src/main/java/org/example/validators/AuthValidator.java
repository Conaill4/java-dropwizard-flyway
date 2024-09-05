package org.example.validators;

import org.example.exceptions.Entity;
import org.example.exceptions.PasswordException;
import org.example.exceptions.EmailException;
import org.example.models.LoginRequest;

public class AuthValidator {
    public boolean isLoginValid(final LoginRequest loginRequest) throws
            EmailException, PasswordException {
        if (loginRequest.getEmail() == ""
                || loginRequest.getEmail() == null) {
            throw new EmailException(Entity.EMAIL, " is empty.");
        }
        if (loginRequest.getPassword() == ""
                || loginRequest.getPassword() == null) {
            throw new PasswordException(Entity.PASSWORD, " is empty.");
        }
        if (loginRequest.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])"
                + "(?=.*[A-Z])(?=.*[@#$%^&+=])(?=/S+$).{8,}$")) {
            throw new PasswordException(Entity.PASSWORD,
                    " is invalid.");
        }
        return true;
    }
}
