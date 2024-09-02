package org.example.dao;

import org.example.daos.JobRoleDao;
import org.example.mappers.JobRoleMapper;
import org.example.models.JobRole;
import org.example.models.JobRoleResponse;
import org.example.services.JobRoleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobRoleDaoTest {

    JobRole jobRole = new JobRole(
            1,
            "Manager",
            "Derry",
            "Intern",
            "Grade 1 -£20,000 - 25,000",
            Date.valueOf("2024-12-30"));

    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);

    @Test
    void getJobRoles_DAOshouldReturnListOfJobRoles()
            throws SQLException {
        List<JobRole> expectedJobRoles = new ArrayList<>();
        expectedJobRoles.add(jobRole);

        List<JobRole> result = new ArrayList<>();
        result.add(jobRole);

        Mockito.when(jobRoleDao.getJobRoles()).thenReturn(expectedJobRoles);

        assertEquals(expectedJobRoles, result);
    }

    @Test
    void getJobRoles_DAOshouldReturnSQLException()
            throws SQLException {
        Mockito.when(jobRoleDao.getJobRoles()).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleDao.getJobRoles());
    }
}
