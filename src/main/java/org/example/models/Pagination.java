package org.example.models;

public class Pagination {
    int totalPages;


    public Pagination(final int totalPage) {
        this.totalPages = totalPage;
    }

    public int getTotalPage() {
        return totalPages;
    }



    public void setTotalPage(final int totalPage) {
        this.totalPages = totalPage;
    }


}



