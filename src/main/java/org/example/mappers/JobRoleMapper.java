package org.example.mappers;

import org.example.models.JobRoleDetailedResponse;
import org.example.models.JobRoleResponse;

import java.util.List;
import java.util.stream.Collectors;

public class JobRoleMapper {
    public List<JobRoleResponse> mapJobRoleListToJobRoleResponseList(
            final List<JobRoleResponse> jobRoles) {
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

    public List<JobRoleDetailedResponse>
    mapJobRoleListToJobRoleDetailedResponse(
            final List<JobRoleDetailedResponse> jobRoles) {
        return jobRoles
                .stream()
                .map(jobRole -> new JobRoleDetailedResponse(
                        jobRole.getJobRoleId(),
                        jobRole.getRoleName(),
                        jobRole.getLocation(),
                        jobRole.getCapabilityName(),
                        jobRole.getBandName(),
<<<<<<< HEAD
                        jobRole.getJobDetailed())
=======
                        jobRole.getBasicJobRole()))
>>>>>>> 397c0ec507b5c9a528ff7416290ab10f9db7e8d4
                .collect(Collectors.toList());
    }
}
