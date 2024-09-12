package org.example.service;

import org.example.exceptions.DoesNotExistException;
import org.example.daos.JobRoleDao;
import org.example.exceptions.FailedToCreateException;
import org.example.mappers.JobRoleMapper;
import org.example.models.*;
import org.example.services.JobRoleService;
import org.example.validators.PaginationSanitiser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {

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

    JobRoleRequest jobRoleRequest = new JobRoleRequest(
            "Graduate Software Engineer",
            "Derry",
            2,
            3,
            Date.valueOf("2024-12-30"),
            "Engineering Academy",
            "7 Week academy teaching Programming/Web-Dev/Testing",
            "https://learn.microsoft.com/en-us/sharepoint/dev/general-development/urls-and-tokens-in-sharepoint",
            1);



    @Test
    public void getJobRoles_shouldReturnListOfJobRoles_whenDaoReturnsJobRoleList()
            throws SQLException {
        List<JobRoleResponse> expectedJobRoles = new ArrayList<>();
        Mockito.when(jobRoleMapper.mapJobRoleListToJobRoleResponseList(
                jobRoleDao.getOpenJobRoles(0,10, "jobRoleId", "ASC"))).thenReturn(expectedJobRoles);

        List<JobRoleResponse> JobRoleList = jobRoleService.getAllJobRoles(1,10, "jobRoleId", "ASC");

        assertEquals(expectedJobRoles, JobRoleList);
    }

    @Test
    public void getAllJobRoles_shouldReturnSQLException()
            throws SQLException{
        Mockito.when(jobRoleDao.getOpenJobRoles( 0,10, "jobRoleId", "ASC"))
                .thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleService.getAllJobRoles(1,10, "jobRoleId", "ASC"));
    }

    @Test
    public void getJobRoleById_shouldReturnJobRole_WhenDAOReturnsJobRole() throws SQLException, DoesNotExistException {
        int jobRoleId = jobRoleDetailed1.getJobRole().getJobRoleId();

        Mockito.when(jobRoleDao.getJobRoleById((jobRoleDetailed1.getJobRole().getJobRoleId()))).thenReturn(jobRoleDetailed1);
        Mockito.when(jobRoleMapper.mapJobRoleToJobRoleDetailedResponse(jobRoleDetailed1)).thenReturn(jobRoleDetailedResponse);

        JobRoleDetailedResponse result = jobRoleService.getJobRoleById(jobRoleId);

        assertEquals(jobRoleDetailedResponse, result);
    }

    @Test
    public void getJobRoleById_shouldReturnSQLException() throws SQLException, DoesNotExistException {
        Mockito.when(jobRoleDao.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId())).thenThrow(SQLException.class);

       assertThrows(SQLException.class, () -> jobRoleService.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId()));
    }

    @Test
    public void getJobRoleById_shouldReturnDoesNotExistException() throws SQLException, DoesNotExistException {
        Mockito.when(jobRoleDao.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId())).thenThrow(DoesNotExistException.class);

        assertThrows(DoesNotExistException.class, () -> jobRoleService.getJobRoleById(jobRoleDetailed1.getJobRole().getJobRoleId()));
    }

    @Test
    public void createJobRole_shouldReturnJobRoleId() throws DoesNotExistException, SQLException, FailedToCreateException {
        Mockito.when(jobRoleDao.createJobRole(jobRoleRequest)).thenReturn(1);

        int response = jobRoleService.createJobRole(jobRoleRequest);

        assertEquals(1, response);
    }

    @Test
    public void createJobRole_shouldThrowSQLException() throws DoesNotExistException, SQLException, FailedToCreateException {
        Mockito.when(jobRoleDao.createJobRole(jobRoleRequest)).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleService.createJobRole(jobRoleRequest));
    }
    @Test
    public void createJobRole_shouldThrowFailedToCreateException() throws DoesNotExistException, SQLException, FailedToCreateException {
        Mockito.when(jobRoleDao.createJobRole(jobRoleRequest)).thenThrow(FailedToCreateException.class);

        assertThrows(FailedToCreateException.class, () -> jobRoleService.createJobRole(jobRoleRequest));
    }

}
