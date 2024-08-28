package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.services.JobRoleService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


@Api
@Path("/api/job-roles")
public class JobRoleController {
    JobRoleService jobRoleService;
    public JobRoleController(final JobRoleService jobRoleService) {
        this.jobRoleService = jobRoleService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobRoles() {
        try {
            return Response.ok().entity(jobRoleService.getAllJobRoles())
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getJobRoleById(@PathParam("id") final int id) {
        try {
            return Response.ok().entity(jobRoleService.getJobRoleById(id))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
