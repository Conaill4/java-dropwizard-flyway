package org.example.models;

public class Pagination {
    int totalPages;
    int previousPage;
    int nextPage;
    int currentPage;

    public int getPreviousPage() {
        return previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public Pagination(final int totalPage,
                      final int currentPage,
                      final int nextPage,
                      final int previousPage) {
        this.totalPages = totalPage;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
        this.previousPage = previousPage;
    }

    public int getTotalPage() {
        return totalPages;
    }
}



