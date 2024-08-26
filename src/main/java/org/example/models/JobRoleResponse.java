package org.example.models;


import java.util.Date;

public class JobRoleResponse {
    private int jobRoleId;
    private String roleName;
    private String location;
    private int capabilityId;
    private int bandId;
    private Date closingDate;

    public JobRoleResponse(final int jobRoleId, final String roleName,
                           final String location, final int capabilityId,
                           final int bandId, final Date closingDate) {
        this.jobRoleId = jobRoleId;
        this.roleName = roleName;
        this.location = location;
        this.capabilityId = capabilityId;
        this.bandId = bandId;
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
    public int getCapabilityId() {
        return capabilityId;
    }
    public void setCapabilityId(final int capabilityId) {
        this.capabilityId = capabilityId;
    }
    public int getBandId() {
        return bandId;
    }
    public void setBandId(final int bandId) {
        this.bandId = bandId;
    }
    public Date getClosingDate() {
        return closingDate;
    }
    public void setClosingDate(final Date closingDate) {
        this.closingDate = closingDate;
    }
}
