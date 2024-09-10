package org.example.models;

public class RoleOrdering {
    String fieldName;
    String orderBy;

    public RoleOrdering(final String fieldName, final String orderBy) {
        this.fieldName = fieldName;
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(final String orderBy) {
        this.orderBy = orderBy;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }
}
