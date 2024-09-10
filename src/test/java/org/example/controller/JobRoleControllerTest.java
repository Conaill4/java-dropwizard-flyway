package org.example.controller;

import org.example.exceptions.DoesNotExistException;
import org.example.controllers.JobRoleController;
import org.example.models.JobRole;
import org.example.models.JobRoleDetailedResponse;
import org.example.models.JobRoleResponse;
import org.example.models.Pagination;
import org.example.services.JobRoleService;
import org.example.validators.PaginationSanitiser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class JobRoleControllerTest {
    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);
    PaginationSanitiser paginationSanitiser = Mockito.mock(PaginationSanitiser.class);
    private final JobRoleController jobRoleController = new JobRoleController(jobRoleService,paginationSanitiser);
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
    public void getJobRoles_shouldReturnListOfJobs() throws SQLException {
        int page = 1;
        int pageSize = 10;
        int totalRecords = 22;
        int sanitisedPageSize = 10;
        int sanitisedPage = 1;

        List<JobRoleResponse> mockJobRoles = Arrays.asList(jobRole1, jobRole2);
        Pagination pagination = new Pagination(10, sanitisedPage, 2, 0);

        when(jobRoleService.getTotalRecords()).thenReturn(totalRecords);
        when(paginationSanitiser.sanitisePageSize(pageSize)).thenReturn(sanitisedPageSize);
        when(paginationSanitiser.sanitisePage(page, sanitisedPageSize, totalRecords)).thenReturn(sanitisedPage);
        when(jobRoleService.getAllJobRoles(sanitisedPage, sanitisedPageSize)).thenReturn(mockJobRoles);
        when(jobRoleService.getTotalpages(sanitisedPageSize, sanitisedPage)).thenReturn(10);

        Response response = jobRoleController.getAllJobRoles(sanitisedPage, sanitisedPageSize);

        assertEquals(OK.getStatusCode(), response.getStatus());

        Map<String, Object> entity = (Map<String, Object>) response.getEntity();
        assertEquals(mockJobRoles, entity.get("jobRoles"));
        Pagination resultPagination = (Pagination) entity.get("pagination");
        assertEquals(10, resultPagination.getTotalPage());
        assertEquals(2, resultPagination.getNextPage());
        assertEquals(0, resultPagination.getPreviousPage());
    }

    @Test
    public void testGetAllJobRoles_SQLException() throws SQLException {
        // Arrange
        int page = 1;
        int pageSize = 10;

        when(jobRoleService.getTotalRecords()).thenReturn(100);
        when(paginationSanitiser.sanitisePageSize(pageSize)).thenReturn(pageSize);
        when(paginationSanitiser.sanitisePage(page, pageSize, 100)).thenReturn(page);
        when(jobRoleService.getAllJobRoles(1, 10)).thenThrow(new SQLException());

        // Act
        Response response = jobRoleController.getAllJobRoles(page, pageSize);

        // Assert
        assertEquals(INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("Error retrieving job roles", response.getEntity());
    }


    @Test
    void getJobRoleById_shouldReturnJobRole() throws SQLException, DoesNotExistException {
        when(jobRoleService.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId())).thenReturn(jobRoleDetailed1);
        Response response = jobRoleController.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId());
        assertEquals(200, response.getStatus());
        assertEquals(jobRoleDetailed1, response.getEntity());
    }

    @Test
    void getJobRoleById_shouldReturnError_whenServiceThrowSQLException() throws SQLException, DoesNotExistException {
        when(jobRoleService.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId())).thenThrow(SQLException.class);
        Response response = jobRoleController.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId());

        assertEquals(INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }
}
