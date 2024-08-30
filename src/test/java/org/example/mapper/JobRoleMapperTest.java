package org.example.mapper;

import org.example.mappers.JobRoleMapper;
import org.example.models.JobRole;
import org.example.models.JobRoleDetailed;
import org.example.models.JobRoleDetailedResponse;
import org.example.models.JobRoleResponse;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobRoleMapperTest {

    JobRoleResponse jobRole1 = new JobRoleResponse(
            1,
            "Manager",
            "Derry",
            "Intern",
            "Grade 1 -£20,000 - 25,000",
            Date.valueOf("2024-12-30"));

    JobRoleDetailed jobRoleDetailed1 = new JobRoleDetailed(
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
    void getJobRoleMapper_shouldReturnJobRoles() {

        JobRoleMapper jobRoleMapper = new JobRoleMapper();
        List<JobRoleResponse> jobRoles = new ArrayList<>();
        jobRoles.add(jobRole1);

        List<JobRoleResponse> jobRoleResponse = jobRoleMapper
                .mapJobRoleListToJobRoleResponseList(jobRoles);

        assertEquals(1, jobRoleResponse.size());
        assertEquals(jobRole1.getJobRoleId(),
                jobRoleResponse.get(0).getJobRoleId());
        assertEquals(jobRole1.getRoleName(),
                jobRoleResponse.get(0).getRoleName());
        assertEquals(jobRole1.getLocation(),
                jobRoleResponse.get(0).getLocation());
        assertEquals(jobRole1.getCapabilityName(),
                jobRoleResponse.get(0).getCapabilityName());
        assertEquals(jobRole1.getBandName(),
                jobRoleResponse.get(0).getBandName());
        assertEquals(jobRole1.getClosingDate(),
                jobRoleResponse.get(0).getClosingDate());
    }

    @Test
    void getJobRoleMapper_shouldReturnDetailedJobRoles() {

        JobRoleMapper jobRoleMapper = new JobRoleMapper();
        List<JobRoleDetailed> jobRoles = new ArrayList<>();
        jobRoles.add(jobRoleDetailed1);

        List<JobRoleDetailedResponse> jobRoleResponse = jobRoleMapper
                .mapJobRoleListToJobRoleDetailedResponse(jobRoles);

        assertEquals(1, jobRoleResponse.size());
        assertEquals(jobRoleDetailed1.getJobRole(),
                jobRoleResponse.get(0).getJobRole());
        assertEquals(jobRoleDetailed1.getDescription(),
                jobRoleResponse.get(0).getDescription());
        assertEquals(jobRoleDetailed1.getResponsibilities(),
                jobRoleResponse.get(0).getResponsibilities());
        assertEquals(jobRoleDetailed1.getSharepointUrl(),
                jobRoleResponse.get(0).getSharepointUrl());
        assertEquals(jobRoleDetailed1.getNumberOfOpenPositions(),
                jobRoleResponse.get(0).getNumberOfOpenPositions());
        assertEquals(jobRoleDetailed1.getStatus(),
                jobRoleResponse.get(0).getStatus());
    }
}