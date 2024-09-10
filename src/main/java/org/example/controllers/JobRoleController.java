package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.models.JobRoleResponse;
import org.example.models.Pagination;
import org.example.services.JobRoleService;
import org.example.exceptions.DoesNotExistException;

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
            List<JobRoleResponse> jobRoles = jobRoleService
                    .getAllJobRoles(page, pageSize);
            Pagination pagination = new Pagination(
                    jobRoleService.getTotalpages(pageSize, page),
                    jobRoleService.getCurrentPage(page),
                    jobRoleService.getNextPage(page),
                    jobRoleService.getPreviousPage(page));
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
