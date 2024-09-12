package org.example.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderBySanitiserTest {
    private final OrderBySanitiser orderBySanitiser = new OrderBySanitiser();

    @Test
    public void sanitiseFieldName_WhenFieldNameIsWrong_ReturnsDefault() {
        String fieldName = "roleName123";

        String result = orderBySanitiser.sanitiseFieldName(fieldName);

        assertEquals("jobRoleId", result);
    }

    @Test
    public void sanitiseFieldName_WhenOrderByIsWrong_ReturnsDefault() {
        String orderBy = "123";

        String result = orderBySanitiser.sanitiseOrderBy(orderBy);

        assertEquals("ASC", result);
    }

    @Test
    public void sanitiseFieldName_WhenFieldNameIsCorrect_ReturnsFieldName() {
        String fieldName = "roleName";

        String result = orderBySanitiser.sanitiseFieldName(fieldName);

        assertEquals("roleName", result);
    }

    @Test
    public void sanitiseFieldName_WhenOrderByIsDescending_ReturnsOrderByDescending() {
        String orderBy = "DESC";

        String result = orderBySanitiser.sanitiseOrderBy(orderBy);

        assertEquals("DESC", result);
    }

    @Test
    public void sanitiseFieldName_WhenOrderByIsAscending_ReturnsOrderByAscending() {
        String orderBy = "ASC";

        String result = orderBySanitiser.sanitiseOrderBy(orderBy);

        assertEquals("ASC", result);
    }

    @Test
    public void sanitiseFieldName_WhenFieldNameIsAValidFieldName_ReturnsFieldName() {
        String fieldName1 = "roleName";
        String result1 = orderBySanitiser.sanitiseFieldName(fieldName1);
        assertEquals("roleName", result1);

        String fieldName2 = "jobRoleId";
        String result2 = orderBySanitiser.sanitiseFieldName(fieldName2);
        assertEquals("jobRoleId", result2);

        String fieldName3 = "capabilityName";
        String result3 = orderBySanitiser.sanitiseFieldName(fieldName3);
        assertEquals("capabilityName", result3);

        String fieldName4 = "bandName";
        String result4 = orderBySanitiser.sanitiseFieldName(fieldName4);
        assertEquals("bandName", result4);

        String fieldName5 = "closingDate";
        String result5 = orderBySanitiser.sanitiseFieldName(fieldName5);
        assertEquals("closingDate", result5);
    }
}
