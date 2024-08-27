package org.example.mappers;

import org.example.models.JobRole;
import org.example.models.JobRoleResponse;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class JobRoleMapper {
    public List<JobRoleResponse> mapOrderListToJobRoleResponseList(
            final List<JobRole> jobRoles) {
        return jobRoles
                .stream()
                .map(jobRole -> new JobRoleResponse(jobRole.getJobRoleId(),
                        jobRole.getRoleName(),
                        jobRole.getLocation(),
                        jobRole.getCapabilityName(),
                        jobRole.getBandName(),
                        jobRole.getClosingDate()))
                .collect(Collectors.toList());
    }
}
