package org.example.models;

import java.sql.Date;

public class JobRoleDetailedResponse {
    private int jobRoleId;
    private String roleName;
    private String location;
    private String capabilityName;
    private String bandName;
    private JobDetailedResponse jobDetailed;

    public JobRoleDetailedResponse(final Date closingDate,
                   final String description,
                   final String responsibilities,
                   final String sharepointUrl,
                   final String status,
                    final JobDetailedResponse jobDetailed) {
        this.jobRoleId = jobRoleId;
        this.roleName = roleName;
        this.location = location;
        this.capabilityName = capabilityName;
        this.bandName = bandName;
        this.jobDetailed = jobDetailed;
    }

    public int getJobRoleId() {
        return jobRoleId;
    }

    public void setJobRoleId(final int jobRoleId) {
        this.jobRoleId = jobRoleId;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(final String capabilityName) {
        this.capabilityName = capabilityName;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(final String bandName) {
        this.bandName = bandName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public JobDetailedResponse getJobDetailed() {
        return jobDetailed;
    }
}
