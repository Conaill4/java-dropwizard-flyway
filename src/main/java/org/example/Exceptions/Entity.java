package org.example.Exceptions;

public enum Entity {
    JOBROLE("JobRole"),
    JOBROLEDETAILED("JobRoleDetailed");

    private final String entity;

    Entity(final String entity) {
        this.entity = entity;
    }

    public String getEntity() {
        return entity;
    }
}
