package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class JobRoleResponse {
    private String roleName;
    private String location;
    private int capabilityId;
    private int bandId;
    private Date closingDate;

    @JsonCreator
    public JobRoleResponse(
            @JsonProperty("roleName") final String roleName,
            @JsonProperty("location") final String location,
            @JsonProperty("capabilityId") final int capabilityId,
            @JsonProperty("bandId") final int bandId,
            @JsonProperty("closingDate") final Date closingDate) {
        this.setRoleName(roleName);
        this.setLocation(location);
        this.setCapabilityId(capabilityId);
        this.setBandId(bandId);
        this.setClosingDate(closingDate);
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
