package org.example.daos;

import org.example.exceptions.DoesNotExistException;
import org.example.models.JobRole;
import org.example.models.JobRoleDetailed;
import org.example.models.JobRoleResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {

    public List<JobRoleResponse> getJobRoles(
            final int offset, final int limit) throws SQLException {
        List<JobRoleResponse> jobRoles = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT jobRoleId, roleName, location,"
                    + " Capability.capabilityName, Band.bandName, "
                    + "closingDate FROM `job-roles`"
                    + " JOIN Capability using(capabilityId)"
                    + " JOIN Band using(bandId)"
                    + " WHERE statusId = 1"
                    + " ORDER BY jobRoleId"
                    + " LIMIT ? OFFSET ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, limit);
            statement.setInt(2, offset);
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
    public int countTotalJobs() throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT COUNT(*) "
                    + "FROM `job-roles`JOIN Capability using(capabilityId) "
                    + "JOIN Band using(bandId) "
                    + "WHERE statusId = 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    public JobRoleDetailed getJobRoleById(final int jobRoleId)
            throws SQLException, DoesNotExistException {

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
                JobRoleDetailed jobRole = new JobRoleDetailed(
                        new JobRole(
                                resultSet.getInt("jobRoleId"),
                                resultSet.getString("roleName"),
                                resultSet.getString("location"),
                                resultSet.getString("capabilityName"),
                                resultSet.getString("bandName"),
                                resultSet.getDate("closingDate")),
                        resultSet.getString("description"),
                        resultSet.getString("responsibilities"),
                        resultSet.getString("sharepointUrl"),
                        resultSet.getInt("numberOfOpenPositions"),
                        resultSet.getString("statusName"));
                return jobRole;
            }
        }
        return null;
    }
}
