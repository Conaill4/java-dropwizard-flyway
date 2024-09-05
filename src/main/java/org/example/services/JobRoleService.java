package org.example.services;

import org.example.daos.JobRoleDao;
import org.example.mappers.JobRoleMapper;
import org.example.models.JobRoleDetailedResponse;
import org.example.models.JobRoleResponse;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {
    private final JobRoleDao jobRoleDao;
    private final JobRoleMapper jobRoleMapper;

    public JobRoleService(final JobRoleDao jobRoleDao,
                          final JobRoleMapper jobRoleMapper) {
        this.jobRoleDao = jobRoleDao;
        this.jobRoleMapper = jobRoleMapper;
    }

    public List<JobRoleResponse> getAllJobRoles(
            final int page, final int pageSize) throws SQLException {
        try {
            final int offset = (page - 1) * pageSize;
            return jobRoleMapper.mapJobRoleListToJobRoleResponseList(
                    jobRoleDao.getJobRoles(offset, pageSize));
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    public int getTotalPages(final int pageSize) throws SQLException {
        int totalRecords = jobRoleDao.countTotalJobs();
        return (int) Math.ceil((double) totalRecords / pageSize);
    }

    public List<JobRoleDetailedResponse> getJobRoleById(final int id)
            throws SQLException {
        try {
            return jobRoleMapper.mapJobRoleListToJobRoleDetailedResponse(
                    jobRoleDao.getJobRoleById(id));
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
