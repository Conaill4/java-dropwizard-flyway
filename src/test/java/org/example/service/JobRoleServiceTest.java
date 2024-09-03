package org.example.service;

import org.example.Exceptions.DoesNotExistException;
import org.example.daos.JobRoleDao;
import org.example.mappers.JobRoleMapper;
import org.example.models.JobRole;
import org.example.models.JobRoleDetailed;
import org.example.models.JobRoleDetailedResponse;
import org.example.models.JobRoleResponse;
import org.example.services.JobRoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class JobRoleServiceTest {

    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);
    JobRoleMapper jobRoleMapper = Mockito.mock(JobRoleMapper.class);
    JobRoleService jobRoleService = new JobRoleService(jobRoleDao, jobRoleMapper);

    JobRoleDetailedResponse jobRoleDetailedResponse = new JobRoleDetailedResponse(
            new JobRole(
            2,
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

    JobRoleDetailed jobRoleDetailed1 = new JobRoleDetailed (
            new JobRole(
                    2,
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
    void getJobRoles_shouldReturnListOfJobRoles_whenDaoReturnsJobRoleList()
            throws SQLException {
        List<JobRoleResponse> expectedJobRoles = new ArrayList<>();
        Mockito.when(jobRoleMapper.mapJobRoleListToJobRoleResponseList(
                jobRoleDao.getJobRoles())).thenReturn(expectedJobRoles);

        List<JobRoleResponse> JobRoleList = jobRoleService.getAllJobRoles();

        assertEquals(expectedJobRoles, JobRoleList);
    }

    @Test
    void getAllJobRoles_shouldReturnSQLException()
            throws SQLException{
        Mockito.when(jobRoleDao.getJobRoles())
                .thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleService.getAllJobRoles());
    }

    @Test
    void getJobRoleById_shouldReturnJobRole_WhenDAOReturnsJobRole() throws SQLException, DoesNotExistException {
        int jobRoleId = jobRoleDetailed1.getJobRole().getJobRoleId();

        Mockito.when(jobRoleDao.getJobRoleById((jobRoleDetailed1.getJobRole().getJobRoleId()))).thenReturn(jobRoleDetailed1);
        Mockito.when(jobRoleMapper.mapJobRoleListToJobRoleDetailedResponse(jobRoleDetailed1)).thenReturn(jobRoleDetailedResponse);

        JobRoleDetailedResponse result = jobRoleService.getJobRoleById(jobRoleId);
        
        assertEquals(jobRoleDetailedResponse, result);
    }

    @Test
    void getJobRoleById_shouldReturnSQLException() throws SQLException, DoesNotExistException {
        Mockito.when(jobRoleDao.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId())).thenThrow(SQLException.class);

       assertThrows(SQLException.class, () -> jobRoleService.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId()));
    }

}
