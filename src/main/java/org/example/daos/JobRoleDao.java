package org.example.daos;

import org.example.models.DetailedJobRole;
import org.example.models.JobRoleDetailedResponse;
import org.example.models.JobRoleResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {
    public List<JobRoleResponse> getJobRoles() throws SQLException {
        List<JobRoleResponse> jobRoles = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT jobRoleId, roleName, location,"
                    + " Capability.capabilityName, Band.bandName, "
                    + "closingDate FROM `job-roles`"
                    + " JOIN Capability using(capabilityId)"
                    + " JOIN Band using(bandId)";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JobRoleResponse jobRoleResponse = new JobRoleResponse(
                        resultSet.getInt("jobRoleId"),
                        resultSet.getString("roleName"),
                        resultSet.getString("location"),
                        resultSet.getString("capabilityName"),
                        resultSet.getString("bandName"),
                        resultSet.getDate("closingDate"));
                jobRoles.add(jobRoleResponse);
            }
        }
        return jobRoles;
    }

    public List<JobRoleDetailedResponse> getJobRole(final int jobRoleId)
            throws SQLException {
        List<JobRoleDetailedResponse> jobRoleDetailedResponses =
                new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT jobRoleId, roleName, description, location,"
                    + " responsibilities, sharepointUrl,"
                    + " Capability.capabilityName, Band.bandName,"
                    + " closingDate, numberOfOpenPositions,"
                    + " Status.statusName FROM `job-roles`"
                    + " JOIN Capability using(capabilityId)"
                    + " JOIN Band using(bandId)"
                    + " JOIN Status using (statusId)"
                    + " WHERE `jobRoleId` = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, jobRoleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                JobRoleDetailedResponse jobRole =  new JobRoleDetailedResponse(
                        resultSet.getInt("jobRoleId"),
                        resultSet.getString("roleName"),
                        resultSet.getString("location"),
                        resultSet.getString("capabilityName"),
                        resultSet.getString("bandName"),
                        new DetailedJobRole(
                                resultSet.getDate("closingDate"),
                                resultSet.getString("description"),
                                resultSet.getString("responsibilities"),
                                resultSet.getString("sharepointUrl"),
                                resultSet.getInt("numberOfOpenPositions"),
                                resultSet.getString("statusName")
                        )
                );
                jobRoleDetailedResponses.add(jobRole);
            }
            return jobRoleDetailedResponses;
        }
    }
}
