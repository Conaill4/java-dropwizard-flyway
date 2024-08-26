package org.example.daos;

import org.example.models.JobRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {
    public List<JobRole> getJobRoles() throws SQLException {
        List<JobRole> jobRoles = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT jobRoleId, roleName, location,"
                    + "capability.capabilityName, band.bandName, "
                    + "closingDate FROM job-roles"
                     + "JOIN capability using(capability.id)"
                     + "JOIN band band using(band.id)");
            while (resultSet.next()) {
                JobRole jobRole = new JobRole(
                        resultSet.getInt("jobRoleId"),
                        resultSet.getString("roleName"),
                        resultSet.getString("location"),
                        resultSet.getInt("capabilityId"),
                        resultSet.getInt("bandId"),
                        resultSet.getDate("closingDate"));
                jobRoles.add(jobRole);
            }
        }
        return jobRoles;
    }
}
