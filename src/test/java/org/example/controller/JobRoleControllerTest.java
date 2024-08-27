package org.example.controller;

import org.example.controllers.JobRoleController;
import org.example.models.JobRoleResponse;
import org.example.services.JobRoleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class JobRoleControllerTest {
    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);

    private final JobRoleController jobRoleController = new JobRoleController(jobRoleService);

    JobRoleResponse jobRole1 = new JobRoleResponse(
            1,
            "Manager",
            "Derry",
            "Intern",
            "Grade 1 -£20,000 - 25,000",
            Date.valueOf("2024-12-30"));

    JobRoleResponse jobRole2 = new JobRoleResponse(1,
            "Tech Lead",
            "Derry",
            "Graduate",
            "Grade 1 -£20,000 - 25,000",
            Date.valueOf("2024-12-30"));

    @Test
    void getJobRoles_shouldReturnResponseCode200() throws SQLException {
        List<JobRoleResponse> mockJobRoles = Arrays.asList(jobRole1, jobRole2);
        Mockito.when(jobRoleService.getAllJobRoles()).thenReturn(mockJobRoles);
        Response response = jobRoleController.getAllJobRoles();
        assertEquals(200, response.getStatus());
        assertEquals(mockJobRoles, response.getEntity());
    }

    @Test
    void getAllJobRoles_shouldThrowSQLException_whenServiceThrowsException() throws SQLException {
        Mockito.when(jobRoleService.getAllJobRoles()).thenThrow(SQLException.class);
        Response response = jobRoleController.getAllJobRoles();

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }
}
