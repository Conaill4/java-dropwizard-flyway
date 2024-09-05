package org.example.exceptions;

public class EmailException extends Throwable {
    public EmailException(final Entity entity, final String reason) {
        super(entity.getEntity() + " is not valid: " + reason);
    }
}
