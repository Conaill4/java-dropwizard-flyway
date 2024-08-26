package org.example.service;

import org.example.daos.JobRoleDao;
import org.example.models.JobRole;
import org.example.models.JobRoleResponse;
import org.example.services.JobRoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class JobRoleServiceTest {

    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);
    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);

    Connection conn;

    @Test
    void getEmployees_shouldReturnListOfEmployees_whenDaoReturnsEmployeeList()
            throws SQLException {
        List<JobRole> expectedJobRoles = new ArrayList<>();
        Mockito.when(jobRoleDao.getJobRoles()).thenReturn(expectedJobRoles);

        List<JobRoleResponse> JobRoleList = jobRoleService.getAllJobRoles();

        assertEquals(expectedJobRoles, JobRoleList);
    }

    @Test
    void getEmployees_shouldReturnSQLException_whenDaoThrowsSQLException()
            throws SQLException{
        Mockito.when(jobRoleDao.getJobRoles()).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobRoleService.getAllJobRoles());
    }

}
