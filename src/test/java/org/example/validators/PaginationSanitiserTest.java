package org.example.validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaginationSanitiserTest {

    private final PaginationSanitiser paginationSanitiser = new PaginationSanitiser();

    @Test
    void sanitisePage_WhenPageIsZero_ReturnsMinPage() {
        int page = 0;
        int sanitisedPageSize = 10;
        int totalRecords = 50;

        int result = paginationSanitiser.sanitisePage(page, sanitisedPageSize, totalRecords);

        assertEquals(1, result);
    }

    @Test
    void sanitisePage_WhenPageIsNegative_ReturnsMinPage() {
        int page = -5;
        int sanitisedPageSize = 10;
        int totalRecords = 50;

        int result = paginationSanitiser.sanitisePage(page, sanitisedPageSize, totalRecords);

        assertEquals(1, result);
    }

    @Test
    void sanitisePage_WhenPageIsGreaterThanMaxPage_ReturnsMaxPage() {
        int page = 10;
        int sanitisedPageSize = 10;
        int totalRecords = 50; // Max pages should be 5

        int result = paginationSanitiser.sanitisePage(page, sanitisedPageSize, totalRecords);

        assertEquals(5, result);
    }

    @Test
    void sanitisePage_WhenPageIsWithinRange_ReturnsSamePage() {
        int page = 3;
        int sanitisedPageSize = 10;
        int totalRecords = 50; // Max pages should be 5

        int result = paginationSanitiser.sanitisePage(page, sanitisedPageSize, totalRecords);

        assertEquals(3, result);
    }

    @Test
    void sanitisePageSize_WhenPageSizeIsNotValid_ReturnsValidPageSize() {
        int pageSize = 5; // Invalid page size

        int result = paginationSanitiser.sanitisePageSize(pageSize);

        assertEquals(10, result);
    }

    @Test
    void sanitisePageSize_WhenPageSizeIsValid_ReturnsValidPageSize() {
        int pageSize = 10; // Valid page size

        int result = paginationSanitiser.sanitisePageSize(pageSize);

        assertEquals(10, result);
    }
}

