package org.example.exceptions;

public class PasswordException extends Throwable {
    public PasswordException(final Entity entity, final String reason) {
        super(entity.getEntity() + " is not valid: " + reason);
    }
}
