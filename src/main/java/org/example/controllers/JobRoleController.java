package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.models.JobRoleResponse;
import org.example.models.Pagination;
import org.example.services.JobRoleService;
import org.example.exceptions.DoesNotExistException;
import org.example.validators.PaginationSanitiser;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api("JobRole API")
@Path("/api/job-roles")
public class JobRoleController {
    JobRoleService jobRoleService;
    public JobRoleController(final JobRoleService jobRoleService) {
        this.jobRoleService = jobRoleService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobRoles(
            @QueryParam("page") @DefaultValue("1") final int page,
            @QueryParam("pageSize") @DefaultValue("10") final int pageSize) {
        try {
            PaginationSanitiser paginationSanitiser = new PaginationSanitiser();
            int totalRecords = jobRoleService.getTotalRecords();
            int sanitisedPageSize = PaginationSanitiser.sanitisePageSize(
                    pageSize);
            int sanitisedPage = paginationSanitiser.sanitisePage(
                    page, sanitisedPageSize, totalRecords);


            List<JobRoleResponse> jobRoles = jobRoleService
                    .getAllJobRoles(sanitisedPage, sanitisedPageSize);
            Pagination pagination = new Pagination(
                    jobRoleService.getTotalpages(sanitisedPageSize,
                            sanitisedPage),
                    sanitisedPage,
                    jobRoleService.getNextPage(sanitisedPage),
                    jobRoleService.getPreviousPage(sanitisedPage));
                Map<String, Object> response = new HashMap<>();
                response.put("jobRoles", jobRoles);
                response.put("pagination", pagination);
                return Response.ok().entity(response).build();
            } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving job roles")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getJobRoleById(@PathParam("id") final int id) {
        try {
            return Response.ok().entity(jobRoleService.getJobRoleById(id))
                    .build();
        } catch (SQLException e) {
            return Response.serverError().build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }
}
