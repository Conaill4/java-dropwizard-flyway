package org.example.validators;

import org.example.models.JobRole;
import org.example.models.JobRoleRequest;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class JobRoleValidatorTests {

    private final JobRoleValidator jobRoleValidator = new JobRoleValidator();

    @Test
  public void JobRoleValidator_ShouldThrowRoleNameTooLong() throws Exception {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Graduate Software Engineeraaasdfghjklsdfghjkldfghjklasdfghjklsdfghjkasdfghjkwertyuiowertyu",
                "Derry",
                2,
                3,
                Date.valueOf("2024-12-30"),
                "Engineering Academy",
                "7 Week academy teaching Programming/Web-Dev/Testing",
                "https://learn.microsoft.com/en-us/sharepoint/dev/general-development/urls-and-tokens-in-sharepoint",
                1
        );

        assertTrue(jobRoleValidator.validateJobRole(jobRoleRequest));
    }


@Test
public void JobRoleValidator_ShouldThrowRoleLocationTooLong() throws Exception {
    JobRoleRequest jobRoleRequest = new JobRoleRequest(
            "Graduate Software Engineer",
            "DerryGraduate Software Engineeraaasdfghjklsdfghjkldfghjklasdfghjklsdfghjkasdfghjkwertyuiowertyu",
            2,
            3,
            Date.valueOf("2024-12-30"),
            "Engineering Academy",
            "7 Week academy teaching Programming/Web-Dev/Testing",
            "https://learn.microsoft.com/en-us/sharepoint/dev/general-development/urls-and-tokens-in-sharepoint",
            1
    );

    assertTrue(jobRoleValidator.validateJobRole(jobRoleRequest));
}

    @Test
    public void JobRoleValidator_ShouldThrowRoleDescriptionTooLong() throws Exception {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Graduate Software Engineer",
                "Derry",
                2,
                3,
                Date.valueOf("2024-12-30"),
                "Engineering AcademyDerryGraduate Software Engineeraaasdfghjklsdfghjkldfghjklasdfghjklsdfghjkasdfghjkwertyuiowertyu",
                "7 Week academy teaching Programming/Web-Dev/Testing",
                "https://learn.microsoft.com/en-us/sharepoint/dev/general-development/urls-and-tokens-in-sharepoint",
                1
        );

        assertTrue(jobRoleValidator.validateJobRole(jobRoleRequest));
    }
    @Test
    public void JobRoleValidator_ShouldThrowRoleNumberOfOpenPositionsIsLessThanOne() throws Exception {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Graduate Software Engineer",
                "Derry",
                2,
                3,
                Date.valueOf("2024-12-30"),
                "Engineering Academy",
                "7 Week academy teaching Programming/Web-Dev/Testing",
                "https://learn.microsoft.com/en-us/sharepoint/dev/general-development/urls-and-tokens-in-sharepoint",
                2
        );
        assertTrue(jobRoleValidator.validateJobRole(jobRoleRequest));
    }

    @Test
    public void JobRoleValidator_ShouldThrowRoleResponsibilityTooLong() throws Exception {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Graduate Software Engineer",
                "Derry",
                2,
                3,
                Date.valueOf("2024-12-30"),
                "Engineering Academy",
                "asdfghjk",
                "https://learn.microsoft.com/en-us/sharepoint/dev/general-development/urls-and-tokens-in-sharepoint",
                1
        );

        assertTrue(jobRoleValidator.validateJobRole(jobRoleRequest));
    }
}