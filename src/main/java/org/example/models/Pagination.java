package org.example.models;

public class Pagination {
    int totalPages;
    int previousPage;
    int nextPage;
    int currentPage;

    public int getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(final int previousPage) {
        this.previousPage = previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(final int nextPage) {
        this.nextPage = nextPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final int currentPage) {
        this.currentPage = currentPage;
    }




    public Pagination(final int totalPage, final int currentPage,
                      final int nextPage, final int previousPage) {
        this.totalPages = totalPage;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
        this.previousPage = previousPage;
    }

    public int getTotalPage() {
        return totalPages;
    }



    public void setTotalPage(final int totalPage) {
        this.totalPages = totalPage;
    }


}



