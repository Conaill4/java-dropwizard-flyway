package org.example.dao;

import org.example.daos.JobRoleDao;
import org.example.models.JobRole;
import org.example.models.JobRoleDetailed;
import org.example.models.JobRoleResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobRoleDaoTest {

    JobRoleResponse jobRole = new JobRoleResponse(
            1,
            "Manager",
            "Derry",
            "Intern",
            "Grade 1 -£20,000 - 25,000",
            Date.valueOf("2024-12-30"));

    JobRoleDetailed jobRoleDetailed1 = new JobRoleDetailed(
            new JobRole(
                    3,
                    "Manager",
                    "Derry",
                    "Senior",
                    "Grade 5 -£50,001+",
                    Date.valueOf("2024-12-28")
            ),
            "Kainos Senior Front End Developer",
            "Managing front end projects for clients",
            "https://learn.microsoft.com/en-us/sharepoint/dev/general-development/urls-and-tokens-in-sharepoint",
            1,
            "OPEN"
    );

    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);

    @Test
    void getJobRoles_DAOshouldReturnListOfJobRolesById()
            throws SQLException {
        List<JobRoleResponse> expectedJobRoles = new ArrayList<>();
        expectedJobRoles.add(jobRole);

        List<JobRoleResponse> result = new ArrayList<>();
        result.add(jobRole);

        Mockito.when(jobRoleDao.getJobRoles(0,10)).thenReturn(expectedJobRoles);

        assertEquals(expectedJobRoles, result);
    }

    @Test
    void getJobRoles_DAOshouldReturnSQLExceptionById()
            throws SQLException {
        Mockito.when(jobRoleDao.getJobRoles(0,10)).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleDao
                .getJobRoles(0,10));
    }

    @Test
    void getJobRoles_DAOshouldReturnDetailedJobRoleById()
            throws SQLException {
        List<JobRoleDetailed> expectedJobRole = new ArrayList<>();
        expectedJobRole.add(jobRoleDetailed1);

        int id = jobRoleDetailed1.getJobRole().getJobRoleId();

        List<JobRoleDetailed> result = new ArrayList<>();
        result.add(jobRoleDetailed1);

        Mockito.when(jobRoleDao.getJobRoleById(id)).thenReturn(expectedJobRole);

        assertEquals(expectedJobRole, result);
    }

    @Test
    void getJobRolesById_DAOshouldReturnSQLExceptionById()
            throws SQLException {

        int id = 1;
        Mockito.when(jobRoleDao.getJobRoleById(id)).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleDao.getJobRoleById(id));
    }
}
