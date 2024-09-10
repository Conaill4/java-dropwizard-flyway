package org.example.models;

public class Pagination {
    private int totalPages;
    private int previousPage;
    private int nextPage;
    private int currentPage;
    public Pagination(final int totalPage,
                      final int currentPage,
                      final int nextPage,
                      final int previousPage) {
        this.totalPages = totalPage;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
        this.previousPage = previousPage;
    }
    public int getPreviousPage() {
        return previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getTotalPage() {
        return totalPages;
    }
}



