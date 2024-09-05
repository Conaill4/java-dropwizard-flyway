package org.example.validators;

import org.example.exceptions.Entity;
import org.example.exceptions.PasswordException;
import org.example.exceptions.EmailException;
import org.example.models.LoginRequest;

public class AuthValidator {
    public boolean isLoginValid(final LoginRequest loginRequest) throws
            EmailException, PasswordException {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        if (email == "" || email == null) {
            throw new EmailException(Entity.EMAIL, " is empty.");
        }
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]"
                + "+\\.[A-Za-z]{2,}$")) {
            throw new EmailException(Entity.EMAIL,
                    " is invalid.");
        }
        if (password == "" || password == null) {
            throw new PasswordException(Entity.PASSWORD, " is empty.");
        }
        if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)"
                + "(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,}$")) {
            throw new PasswordException(Entity.PASSWORD,
                    " is invalid.");
        }
        return true;
    }
}
