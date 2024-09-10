package org.example.services;

import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.daos.JobRoleDao;
import org.example.mappers.JobRoleMapper;
import org.example.models.JobRoleDetailed;
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

    public List<JobRoleResponse> getAllJobRoles(final String fieldName, final String orderBy) throws SQLException {

        return jobRoleMapper.mapJobRoleListToJobRoleResponseList(
                jobRoleDao.getJobRoles(fieldName, orderBy));
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
}
