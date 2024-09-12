package org.example.validators;

import java.util.HashSet;
import java.util.Set;

public class OrderBySanitiser {
    public static final String DEFAULT_ORDER = "ASC";
    public static final String DEFAULT_FIELD_NAME = "jobRoleId";

    public static final Set<String> VALID_FIELDS = new HashSet<>();

    public String sanitiseFieldName(final String fieldName) {

        VALID_FIELDS.add("jobRoleId");
        VALID_FIELDS.add("roleName");
        VALID_FIELDS.add("location");
        VALID_FIELDS.add("capabilityName");
        VALID_FIELDS.add("bandName");
        VALID_FIELDS.add("closingDate");

        if (fieldName == null || (!VALID_FIELDS.contains(fieldName))) {
            return DEFAULT_FIELD_NAME;
        }
        return fieldName;

    }

    public String sanitiseOrderBy(final String orderBy) {
        if (orderBy == null || (!orderBy.equalsIgnoreCase("ASC")
                && !orderBy.equalsIgnoreCase("DESC"))) {
            return DEFAULT_ORDER;
        }
        return orderBy.toUpperCase();
    }
}


