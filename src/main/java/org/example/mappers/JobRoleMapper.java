package org.example.mappers;

import org.example.models.JobRole;
import org.example.models.JobRoleDetailedResponse;
import org.example.models.JobRoleDetailed;
import org.example.models.JobRoleResponse;

import java.util.List;
import java.util.stream.Collectors;

public class JobRoleMapper {
    public List<JobRoleResponse> mapJobRoleListToJobRoleResponseList(
            final List<JobRole> jobRoles) {
        return jobRoles
                .stream()
                .map(jobRole -> new JobRoleResponse(
                        jobRole.getJobRoleId(),
                        jobRole.getRoleName(),
                        jobRole.getLocation(),
                        jobRole.getCapabilityName(),
                        jobRole.getBandName(),
                        jobRole.getClosingDate()))
                .collect(Collectors.toList());
    }

    public JobRoleDetailedResponse mapJobRoleToJobRoleDetailedResponse(
            final JobRoleDetailed jobRole) {
        return new JobRoleDetailedResponse(
            jobRole.getJobRole(),
            jobRole.getDescription(),
            jobRole.getResponsibilities(),
            jobRole.getSharepointUrl(),
            jobRole.getNumberOfOpenPositions(),
            jobRole.getStatus());
    }
}
