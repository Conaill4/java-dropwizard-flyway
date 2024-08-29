package org.example.models;

public class JobRoleDetailedResponse {
    private int jobRoleId;
    private String roleName;
    private String location;
    private String capabilityName;
    private String bandName;

    public BasicJobRole getBasicJobRole() {
        return basicJobRole;
    }

    public void setBasicJobRole(final BasicJobRole basicJobRole) {
        this.basicJobRole = basicJobRole;
    }

    private BasicJobRole basicJobRole;

    public JobRoleDetailedResponse(final int jobRoleId,
                   final String roleName,
                   final String location,
                   final String capabilityName,
                   final String bandName,
                   final BasicJobRole basicJobRole) {
        this.jobRoleId = jobRoleId;
        this.roleName = roleName;
        this.location = location;
        this.capabilityName = capabilityName;
        this.bandName = bandName;
        this.basicJobRole = basicJobRole;
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
}
