package org.example.daos;

import org.example.models.JobRole;

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
                    + " JOIN Band using(bandId)";
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
}
