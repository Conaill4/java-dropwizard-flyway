package org.example;

import org.example.exceptions.Entity;
import org.example.exceptions.PasswordException;
import org.example.exceptions.EmailException;
import org.example.models.LoginRequest;

public class AuthValidator {
    public boolean isLoginValid(final LoginRequest loginRequest) throws
            EmailException, PasswordException {
        if (loginRequest.getEmail() == null) {
            throw new EmailException(Entity.EMAIL, " Email is empty.");
        }
        if (loginRequest.getPassword() == null) {
            throw new PasswordException(Entity.PASSWORD, " Password is empty.");
        }

        return true;
    }
}
