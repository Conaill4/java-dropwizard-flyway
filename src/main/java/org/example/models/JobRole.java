package org.example.models;

public class JobRole {
    private int jobRoleId;
    private String roleName;
    private String location;
    private String capabilityName;
    private String bandName;
    private DetailedJobRole detailedJobRole;

    public DetailedJobRole getBasicJobRole() {
        return detailedJobRole;
    }

    public void setBasicJobRole(final DetailedJobRole detailedJobRole) {
        this.detailedJobRole = detailedJobRole;
    }
    public JobRole(final int jobRoleId,
                   final String roleName,
                   final String location,
                   final String capabilityName,
                   final String bandName,
                   final DetailedJobRole detailedJobRole) {
        this.jobRoleId = jobRoleId;
        this.roleName = roleName;
        this.location = location;
        this.capabilityName = capabilityName;
        this.bandName = bandName;
        this.detailedJobRole = detailedJobRole;
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
