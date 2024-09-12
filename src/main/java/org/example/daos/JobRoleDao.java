package org.example.daos;

import org.example.exceptions.DoesNotExistException;
import org.example.models.JobRole;
import org.example.models.JobRoleDetailed;
import org.example.models.JobRoleRequest;
import org.example.models.JobRoleResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {
    private static final int OPEN = 1;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE = 9;
    private static final int TEN = 10;
    private static final int ELEVEN = 11;
    public List<JobRoleResponse> getOpenJobRoles(
            final int offset, final int limit, final String fieldName,
            final String orderBy)
            throws SQLException {
        List<JobRoleResponse> jobRoles = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT jobRoleId, roleName, location,"
                    + " Capability.capabilityName, Band.bandName, "
                    + "closingDate FROM `job-roles`"
                    + " JOIN Capability using(capabilityId)"
                    + " JOIN Band using(bandId)"
                    + " WHERE statusId = " + OPEN
                    + " ORDER BY " + fieldName
                    + " COLLATE utf8mb4_general_ci " + orderBy
                    + " LIMIT ? OFFSET ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(ONE, limit);
            statement.setInt(TWO, offset);
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
    public int getTotalOpenJobs() throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT COUNT(*) "
                    + "FROM `job-roles` "
                    + "WHERE statusId = " + OPEN + ";";
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
            statement.setInt(ONE, jobRoleId);
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
    public int createJobRole(final JobRoleRequest jobRoleRequest)
            throws SQLException, DoesNotExistException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO `job-roles`"
                    + " (roleName, location,"
                    + " capabilityId, bandId, closingDate,"
                    + " description, responsibilities,"
                    + " sharepointUrl, numberOfOpenPositions, statusId)"
                    + " VALUES"
                    + " (?, ?, ?, ?, ?, ?, ?, ?, ?, '1')";
            PreparedStatement statement = connection.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS);
                    statement.setString(
                            ONE, jobRoleRequest.getRoleName());
                    statement.setString(
                            TWO, jobRoleRequest.getLocation());
                    statement.setInt(
                            THREE, jobRoleRequest.getCapabilityId());
                    statement.setInt(
                            FOUR, jobRoleRequest.getBandId());
                    statement.setDate(
                            FIVE, jobRoleRequest.getClosingDate());
                    statement.setString(
                            SIX, jobRoleRequest.getDescription());
                    statement.setString(
                            SEVEN, jobRoleRequest.getResponsibilities());
                    statement.setString(
                            EIGHT, jobRoleRequest.getSharepointUrl());
                    statement.setInt(
                            NINE, jobRoleRequest.getNumberOfOpenPositions());
                    statement.executeUpdate();
                    ResultSet results = statement.getGeneratedKeys();
                    if (results.next()) {
                    return results.getInt(1);
                    }
                    return -1;
            }
        }
    }

