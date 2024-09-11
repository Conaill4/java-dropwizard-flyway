package org.example.controller;

import org.example.exceptions.DoesNotExistException;
import org.example.controllers.JobRoleController;
import org.example.models.*;
import org.example.services.JobRoleService;
import org.example.validators.OrderBySanitiser;
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
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


public class JobRoleControllerTest {
    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);
    PaginationSanitiser paginationSanitiser = Mockito.mock(PaginationSanitiser.class);
    OrderBySanitiser orderBySanitiser = Mockito.mock(OrderBySanitiser.class);
    private final JobRoleController jobRoleController = new JobRoleController(jobRoleService, paginationSanitiser, orderBySanitiser);
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

    List<JobRoleResponse> jobRolesDesc = Arrays.asList(jobRole2, jobRole1);
    List<JobRoleResponse> jobRolesAsc = Arrays.asList(jobRole1, jobRole2);


    @Test
    public void getJobRoles_shouldReturnListOfJobs() throws SQLException {
        int page = 1;
        int pageSize = 10;
        int totalRecords = 22;
        int sanitisedPageSize = 10;
        int sanitisedPage = 1;
        String orderBy = "ASC";
        String fieldName = "jobRoleId";
        String sanitisedOrderBy = "ASC";
        String sanitisedFieldName = "jobRoleId";

        List<JobRoleResponse> mockJobRoles = Arrays.asList(jobRole1, jobRole2);
        Pagination pagination = new Pagination(10, sanitisedPage, 2, 0);

        when(jobRoleService.getTotalRecords()).thenReturn(totalRecords);
        when(orderBySanitiser.sanitiseOrderBy(orderBy)).thenReturn(sanitisedOrderBy);
        when(orderBySanitiser.sanitiseFieldName(fieldName)).thenReturn(sanitisedFieldName);
        when(paginationSanitiser.sanitisePageSize(pageSize)).thenReturn(sanitisedPageSize);
        when(paginationSanitiser.sanitisePage(page, sanitisedPageSize, totalRecords)).thenReturn(sanitisedPage);
        when(jobRoleService.getAllJobRoles(sanitisedPage, sanitisedPageSize, sanitisedFieldName, sanitisedOrderBy)).thenReturn(mockJobRoles);
        when(jobRoleService.getTotalpages(sanitisedPageSize, sanitisedPage)).thenReturn(10);

        Response response = jobRoleController.getAllJobRoles(sanitisedFieldName, sanitisedOrderBy, sanitisedPage, sanitisedPageSize);

        assertEquals(OK.getStatusCode(), response.getStatus());

        Map<String, Object> entity = (Map<String, Object>) response.getEntity();
        assertEquals(mockJobRoles, entity.get("jobRoles"));
        Pagination resultPagination = (Pagination) entity.get("pagination");
        assertEquals(10, resultPagination.getTotalPage());
        assertEquals(2, resultPagination.getNextPage());
        assertEquals(0, resultPagination.getPreviousPage());
    }

    @Test
    public void getAllJobRoles_SQLException() throws SQLException {

        int page = 1;
        int pageSize = 10;
        String fieldName = "roleName";
        String orderBy = "ASC";

        when(jobRoleService.getTotalRecords()).thenReturn(100);
        when(orderBySanitiser.sanitiseFieldName(fieldName)).thenReturn(fieldName);
        when(orderBySanitiser.sanitiseOrderBy(orderBy)).thenReturn(orderBy);
        when(paginationSanitiser.sanitisePageSize(pageSize)).thenReturn(pageSize);
        when(paginationSanitiser.sanitisePage(page, pageSize, 100)).thenReturn(page);
        when(jobRoleService.getAllJobRoles(1, 10, "roleName", "ASC")).thenThrow(new SQLException());


        Response response = jobRoleController.getAllJobRoles(fieldName, orderBy, page, pageSize);


        assertEquals(INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("Error retrieving job roles", response.getEntity());
    }


    @Test
    public void getJobRoleById_shouldReturnJobRole() throws SQLException, DoesNotExistException {
        when(jobRoleService.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId())).thenReturn(jobRoleDetailed1);
        Response response = jobRoleController.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId());
        assertEquals(200, response.getStatus());
        assertEquals(jobRoleDetailed1, response.getEntity());
    }

    @Test
    public void getJobRoleById_shouldReturnError_whenServiceThrowSQLException() throws SQLException, DoesNotExistException {
        when(jobRoleService.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId())).thenThrow(SQLException.class);
        Response response = jobRoleController.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId());

        assertEquals(INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }

    @Test
    void getJobRoles_shouldReturnListOfJobs_orderedByJobRoleID_inDescendingOrder() throws SQLException {

        int page = 1;
        int pageSize = 10;
        String fieldName = "jobRoleId";
        String orderBy = "DESC";
        String sanitisedFieldName = "jobRoleId";
        String sanitisedOrderBy = "DESC";


        List<JobRoleResponse> jobRolesDesc = Arrays.asList(jobRole2, jobRole1);

        when(jobRoleService.getTotalRecords()).thenReturn(2);
        when(paginationSanitiser.sanitisePageSize(pageSize)).thenReturn(pageSize);
        when(paginationSanitiser.sanitisePage(page, pageSize, 2)).thenReturn(page);
        when(orderBySanitiser.sanitiseFieldName(fieldName)).thenReturn(sanitisedFieldName);
        when(orderBySanitiser.sanitiseOrderBy(orderBy)).thenReturn(sanitisedOrderBy);

        when(jobRoleService.getAllJobRoles(page, pageSize, sanitisedFieldName, sanitisedOrderBy)).thenReturn(jobRolesDesc);
        when(jobRoleService.getTotalpages(pageSize, page)).thenReturn(1);

        when(jobRoleService.getCurrentFieldFilter(sanitisedFieldName)).thenReturn(sanitisedFieldName);
        when(jobRoleService.getCurrentOrderByFilter(sanitisedOrderBy)).thenReturn(sanitisedOrderBy);

        Response response = jobRoleController.getAllJobRoles(fieldName, orderBy, page, pageSize);


        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        Object entity = response.getEntity();

        Map<String, Object> responseBody = (Map<String, Object>) entity;

        List<JobRoleResponse> returnedJobRoles = (List<JobRoleResponse>) responseBody.get("jobRoles");
        RoleOrdering returnedRoleOrdering = (RoleOrdering) responseBody.get("roleOrdering");

        assertEquals(2, returnedJobRoles.get(0).getJobRoleId());
        assertEquals(1, returnedJobRoles.get(1).getJobRoleId());
    }

    @Test
    void getJobRoles_shouldReturnListOfJobs_orderedByJobRoleID_inAscendingOrder() throws SQLException {

        int page = 1;
        int pageSize = 10;
        String fieldName = "jobRoleId";
        String orderBy = "ASC";
        String sanitisedFieldName = "jobRoleId";
        String sanitisedOrderBy = "ASC";

        List<JobRoleResponse> jobRolesAsc = Arrays.asList(jobRole1, jobRole2);

        when(jobRoleService.getTotalRecords()).thenReturn(2);
        when(paginationSanitiser.sanitisePageSize(pageSize)).thenReturn(pageSize);
        when(paginationSanitiser.sanitisePage(page, pageSize, 2)).thenReturn(page);
        when(orderBySanitiser.sanitiseFieldName(fieldName)).thenReturn(sanitisedFieldName);
        when(orderBySanitiser.sanitiseOrderBy(orderBy)).thenReturn(sanitisedOrderBy);

        when(jobRoleService.getAllJobRoles(page, pageSize, sanitisedFieldName, sanitisedOrderBy)).thenReturn(jobRolesAsc);
        when(jobRoleService.getTotalpages(pageSize, page)).thenReturn(1);

        when(jobRoleService.getCurrentFieldFilter(sanitisedFieldName)).thenReturn(sanitisedFieldName);
        when(jobRoleService.getCurrentOrderByFilter(sanitisedOrderBy)).thenReturn(sanitisedOrderBy);

        Response response = jobRoleController.getAllJobRoles(fieldName, orderBy, page, pageSize);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        Object entity = response.getEntity();

        Map<String, Object> responseBody = (Map<String, Object>) entity;

        List<JobRoleResponse> returnedJobRoles = (List<JobRoleResponse>) responseBody.get("jobRoles");
        RoleOrdering returnedRoleOrdering = (RoleOrdering) responseBody.get("roleOrdering");

        assertEquals(1, returnedJobRoles.get(0).getJobRoleId());
        assertEquals(2, returnedJobRoles.get(1).getJobRoleId());
    }
}
