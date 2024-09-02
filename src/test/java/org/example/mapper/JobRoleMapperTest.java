package org.example.mapper;

import org.example.mappers.JobRoleMapper;
import org.example.models.JobRole;
import org.example.models.JobRoleResponse;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobRoleMapperTest {

    JobRole jobRole1 = new JobRole(
            1,
            "Manager",
            "Derry",
            "Intern",
            "Grade 1 -£20,000 - 25,000",
            Date.valueOf("2024-12-30"));

    @Test
    void getJobRoleMapper_shouldReturnJobRoles() throws SQLException{

        JobRoleMapper jobRoleMapper = new JobRoleMapper();
        List<JobRole> jobRoles = new ArrayList<>();
        jobRoles.add(jobRole1);

        List<JobRoleResponse> jobRoleResponse = jobRoleMapper
                .mapOrderListToJobRoleResponseList(jobRoles);

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
}
