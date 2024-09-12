package org.example.validators;

import org.example.exceptions.Entity;
import org.example.exceptions.InvalidException;

import java.sql.SQLException;

public class PaginationValidator {
    private static final int MIN_PAGE_SIZE = 10;
    private static final int MAX_PAGE_SIZE = 100;

    public boolean isPaginationValid(
            final int page,
            final int pageSize,
            final int totalPages)
            throws SQLException, InvalidException {
        if (page <= 0) {
            throw new InvalidException(Entity.PAGINATION, " is invalid");
        }
        if (page > totalPages) {
            throw new InvalidException(Entity.PAGINATION, " is invalid");
        }
        if (pageSize < MIN_PAGE_SIZE) {
            throw new InvalidException(Entity.PAGINATION, " is invalid");
        }
        if (pageSize > MAX_PAGE_SIZE) {
            throw new InvalidException(Entity.PAGINATION, " is invalid");
        }
        return true;
    }
}


