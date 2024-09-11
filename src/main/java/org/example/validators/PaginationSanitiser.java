package org.example.validators;

public class PaginationSanitiser {
    private static final int VALID_PAGE_SIZE = 10;
    private static final int MAX_PAGE_SIZE = 10;
    private static final int MIN_PAGE = 1;
    public int sanitisePage(
            final int page, final int sanatisedPageSize,
            final int totalRecords) {
        if (page <= 0) {
            return MIN_PAGE;
        }
        int maxPage = (int) Math.ceil((double)
                totalRecords / sanatisedPageSize);
        if (page > maxPage) {
            return maxPage;
        }
        return page;
    }

    public int sanitisePageSize(final int pageSize) {
        if (pageSize != VALID_PAGE_SIZE) {
            return VALID_PAGE_SIZE;
        }
        return pageSize;
    }
}
