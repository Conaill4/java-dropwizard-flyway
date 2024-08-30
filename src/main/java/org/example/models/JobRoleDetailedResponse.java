package org.example.models;

import java.sql.Date;

public class JobRoleDetailedResponse {
    private JobRole jobRole;
    private Date closingDate;
    private String description;
    private String responsibilities;

    public JobRole getJobRole() {
        return jobRole;
    }

    public void setJobRole(final JobRole jobRole) {
        this.jobRole = jobRole;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(final Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(final String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getSharepointUrl() {
        return sharepointUrl;
    }

    public void setSharepointUrl(final String sharepointUrl) {
        this.sharepointUrl = sharepointUrl;
    }

    public int getNumberOfOpenPositions() {
        return numberOfOpenPositions;
    }

    public void setNumberOfOpenPositions(final int numberOfOpenPositions) {
        this.numberOfOpenPositions = numberOfOpenPositions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    private String sharepointUrl;
    private int numberOfOpenPositions;
    private String status;


    public JobRoleDetailedResponse(
            final JobRole jobRole,
            final Date closingDate,
            final String description,
            final String responsibilities,
            final String sharepointUrl,
            final int numberOfOpenPositions,
            final String status) {
        this.jobRole = jobRole;
        this.closingDate = closingDate;
        this.description = description;
        this.responsibilities = responsibilities;
        this.sharepointUrl = sharepointUrl;
        this.numberOfOpenPositions = numberOfOpenPositions;
        this.status = status;
    }
}

