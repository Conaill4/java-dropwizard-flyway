package org.example.dao;

import org.example.Exceptions.DoesNotExistException;
import org.example.daos.JobRoleDao;
import org.example.models.JobRole;
import org.example.models.JobRoleDetailed;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobRoleDaoTest {
    private final JobRoleDao jobRoleDao = new JobRoleDao();

    JobRole jobRole = new JobRole(
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

    @Test
    void getJobRoles_DAOShouldReturnJobRoles()
            throws SQLException {
        List<JobRole> expectedJobRoles = new ArrayList<>();
        expectedJobRoles.add(jobRole);

        List<JobRole> result = new ArrayList<>();
        result.add(jobRole);

        jobRoleDao.getJobRoles();

        assertEquals(expectedJobRoles, result);
    }

    @Test
    void getJobRoles_DAOShouldReturnDetailedJobRoleById()
            throws SQLException, DoesNotExistException {

        int id = jobRoleDetailed1.getJobRole().getJobRoleId();

        assertEquals(jobRoleDetailed1.getJobRole().getJobRoleId(), jobRoleDao.getJobRoleById(id).getJobRole().getJobRoleId());
    }

    @Test
    void getJobRoles_DAOShouldThrowDoesNotExistException()
            throws SQLException, DoesNotExistException {

        int id=101;

        assertThrows(DoesNotExistException.class, () -> jobRoleDao.getJobRoleById(id));
    }
}
