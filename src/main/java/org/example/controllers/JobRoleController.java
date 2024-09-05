package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.models.JobRoleResponse;
import org.example.services.JobRoleService;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Api("JobRole API")
@Path("/api/job-roles/")
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
            //validate your params... if invalid - exit...
             //if limit > MAX_ALLOWED (eg 20), then set limit = MAX_ALLOWED

            List<JobRoleResponse> jobRoles = jobRoleService
                    .getAllJobRoles(page, pageSize);
            final int totalPages = jobRoleService.getTotalPages(pageSize);
            return Response.ok()
                    .entity(jobRoles)
                    .header("X-Total-Pages", totalPages)
                    .header("X-Current-Page", page)
                    .header("X-Page-Size", pageSize)
                    .build();
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
        }
    }
}
