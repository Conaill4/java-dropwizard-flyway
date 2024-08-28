package org.example.daos;

import org.example.models.JobRole;
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
}
