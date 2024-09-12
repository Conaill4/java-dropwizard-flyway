package org.example.exceptions;

public class FailedToCreateException extends Exception {
    public FailedToCreateException(final Entity message) {
        super(message.getEntity() + " does not exist");
    }
}
