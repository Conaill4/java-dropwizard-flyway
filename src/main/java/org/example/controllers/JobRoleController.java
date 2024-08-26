package org.example.controllers;

import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Api
@Path("/api/job-roles")
public class JobRoleController {
    JobRoleService jobRoleService;
    public JobRoleController(final JobRoleService jobRoleService) {
        this.jobRoleService = jobRoleService;
    }

    @GET
    @Produces(Med)
}
