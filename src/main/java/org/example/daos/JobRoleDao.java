package org.example.daos;

import org.example.Exceptions.DoesNotExistException;
import org.example.Exceptions.Entity;
import org.example.models.JobRole;
import org.example.models.JobRoleDetailed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {
    public List<JobRole> getJobRoles() throws SQLException {
        List<JobRole> jobRoles = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT jobRoleId, roleName, location,"
                    + " Capability.capabilityName, Band.bandName, "
                    + "closingDate FROM `job-roles`"
                    + " JOIN Capability using(capabilityId)"
                    + " JOIN Band using(bandId)"
                    + " WHERE statusId = 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JobRole jobRole = new JobRole(
                        resultSet.getInt("jobRoleId"),
                        resultSet.getString("roleName"),
                        resultSet.getString("location"),
                        resultSet.getString("capabilityName"),
                        resultSet.getString("bandName"),
                        resultSet.getDate("closingDate"));
                jobRoles.add(jobRole);
            }
        }
        return jobRoles;
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
