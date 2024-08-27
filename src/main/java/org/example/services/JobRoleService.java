package org.example.services;

import org.example.daos.JobRoleDao;
import org.example.mappers.JobRoleMapper;
import org.example.models.JobRoleResponse;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {
    private final JobRoleDao jobRoleDao;

    public JobRoleService(final JobRoleDao jobRoleDao) {
        this.jobRoleDao = jobRoleDao;
    }

    public List<JobRoleResponse> getAllJobRoles() throws SQLException {
            return JobRoleMapper.mapOrderListToJobRoleResponseList(
                    jobRoleDao.getJobRoles());
    }
}
