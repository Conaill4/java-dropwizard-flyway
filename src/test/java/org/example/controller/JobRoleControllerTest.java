package org.example.controller;

import org.example.exceptions.DoesNotExistException;
import org.example.controllers.JobRoleController;
import org.example.models.JobRole;
import org.example.models.JobRoleDetailedResponse;
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

    JobRoleResponse jobRole2 = new JobRoleResponse(
            2,
            "Tech Lead",
            "Derry",
            "Graduate",
            "Grade 1 -£20,000 - 25,000",
            Date.valueOf("2024-12-30"));

    JobRoleDetailedResponse jobRoleDetailed1 = new JobRoleDetailedResponse(
            new JobRole(
                    3,
                    "Manager",
                    "Derry",
                    "Senior",
                    "Grade 5 -£50,001+",
                    Date.valueOf("2024-12-28")
            ),
            "Kainos Senior Front End Developer",
            "Managing front end projects for clients",
            "https://learn.microsoft.com/en-us/sharepoint/dev/general-development/urls-and-tokens-in-sharepoint",
            1,
            "OPEN"

    );

    @Test
    void getJobRoles_shouldReturnListOfEmployees() throws SQLException {
        List<JobRoleResponse> mockJobRoles = Arrays.asList(jobRole1, jobRole2);
        Mockito.when(jobRoleService.getAllJobRoles()).thenReturn(mockJobRoles);
        Response response = jobRoleController.getAllJobRoles();
        assertEquals(200, response.getStatus());
        assertEquals(mockJobRoles, response.getEntity());
    }

    @Test
    void getAllJobRoles_shouldReturnError_whenServiceThrowsSQLException() throws SQLException {
        Mockito.when(jobRoleService.getAllJobRoles()).thenThrow(SQLException.class);
        Response response = jobRoleController.getAllJobRoles();

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }

    @Test
    void getJobRoleById_shouldReturnJobRole() throws SQLException, DoesNotExistException {
        Mockito.when(jobRoleService.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId())).thenReturn(jobRoleDetailed1);
        Response response = jobRoleController.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId());
        assertEquals(200, response.getStatus());
        assertEquals(jobRoleDetailed1, response.getEntity());
    }

    @Test
    void getJobRoleById_shouldReturnError_whenServiceThrowSQLException() throws SQLException, DoesNotExistException {
        Mockito.when(jobRoleService.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId())).thenThrow(SQLException.class);
        Response response = jobRoleController.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId());

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }
}
