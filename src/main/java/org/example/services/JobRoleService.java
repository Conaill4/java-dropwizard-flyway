package org.example.services;

import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.daos.JobRoleDao;
import org.example.exceptions.FailedToCreateException;
import org.example.mappers.JobRoleMapper;
import org.example.models.JobRoleDetailed;
import org.example.models.JobRoleDetailedResponse;
import org.example.models.JobRoleRequest;
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
            final int page, final int pageSize, final String fieldName,
            final String orderBy) throws SQLException {
        final int offset = (page - 1) * pageSize;
        return jobRoleMapper.mapJobRoleListToJobRoleResponseList(
                jobRoleDao.getOpenJobRoles(offset, pageSize,
                        fieldName, orderBy));
}

    public int getTotalPages(final int pageSize)
            throws SQLException {
        int totalRecords = jobRoleDao.getTotalOpenJobs();
        return (int) Math.ceil((double) totalRecords / pageSize);
    }

    public JobRoleDetailedResponse getJobRoleById(final int id)
            throws SQLException, DoesNotExistException {

        JobRoleDetailed jobRoleDetailed = jobRoleDao.getJobRoleById(id);

        if (jobRoleDetailed == null) {
            throw new DoesNotExistException(Entity.JOBROLEDETAILED);
        }
        return jobRoleMapper.mapJobRoleToJobRoleDetailedResponse(
                jobRoleDao.getJobRoleById(id));

    }

    public int getTotalRecords() throws SQLException {
        return jobRoleDao.getTotalOpenJobs();
    }
    public int createJobRole(
            final JobRoleRequest jobRoleRequest)
            throws FailedToCreateException, SQLException,
            DoesNotExistException {
        final int id = jobRoleDao.createJobRole(jobRoleRequest);

        if (id == -1) {
            throw new FailedToCreateException(Entity.JOBROLE);
        }

        return id;

    }
};

