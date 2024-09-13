package org.example.exceptions;

public enum Entity {
    JOBROLE("JobRole"),
    PAGINATION("Pagination"),
    JOBROLEDETAILED("JobRoleDetailed"),
    EMAIL("Email"),
    PASSWORD("Password");

    private final String entity;

    Entity(final String entity) {
        this.entity = entity;
    }
    public String getEntity() {
        return entity;
    }
}
