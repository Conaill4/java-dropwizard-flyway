package org.example.mapper;

import org.example.daos.JobRoleDao;
import org.example.mappers.JobRoleMapper;
import org.example.models.JobRole;
import org.example.models.JobRoleResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobRoleMapperTest {

    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);
    JobRoleMapper jobRoleMapper = Mockito.mock(JobRoleMapper.class);

    @Test
    void getJobRoleMapper_shouldReturnJobRoles() throws SQLException{

        List<JobRole> expectedJobRoles = new ArrayList<>();
        Mockito.when(jobRoleDao.getJobRoles()).thenReturn(expectedJobRoles);

        List<JobRoleResponse> JobRole = jobRoleMapper
                .mapOrderListToJobRoleResponseList(jobRoleDao.getJobRoles());

        assertEquals(expectedJobRoles, JobRole);
    }
}
