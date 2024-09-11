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
    public void sanitiseFieldName_WhenOrderByIsCorrect_ReturnsOrderBy() {
        String orderBy= "DESC";

        String result = orderBySanitiser.sanitiseOrderBy(orderBy);

        assertEquals("DESC", result);
    }
}
