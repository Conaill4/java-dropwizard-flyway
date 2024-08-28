package org.example.models;


import java.util.Date;

public class JobRoleResponse {
    private int jobRoleId;
    private String roleName;
    private String location;
    private String capabilityName;
    private String bandName;
    private Date closingDate;

    public JobRoleResponse(final int jobRoleId, final String roleName,
                           final String location, final String capabilityName,
                           final String bandName, final Date closingDate) {
        this.jobRoleId = jobRoleId;
        this.roleName = roleName;
        this.location = location;
        this.capabilityName = capabilityName;
        this.bandName = bandName;
        this.closingDate = closingDate;
    }

    public int getJobRoleId() {
        return jobRoleId;
    }
    public void setJobRoleId(final int jobRoleId) {
        this.jobRoleId = jobRoleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(final String location) {
        this.location = location;
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
    public Date getClosingDate() {
        return closingDate;
    }
    public void setClosingDate(final Date closingDate) {
        this.closingDate = closingDate;
    }
}
