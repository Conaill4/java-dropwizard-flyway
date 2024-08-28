package org.example.models;

import java.sql.Date;

public class Job {
    final Date closingDate;
    final String description;
    final String responsibilities;
    final String sharepointUrl;
    final int numberOfOpenPositions;
    final String status;

    public Job(
                   final Date closingDate,
                   final String description,
                   final String responsibilities,
                   final String sharepointUrl,
                   final int numberOfOpenPositions,
                   final String status) {
        this.closingDate = closingDate;
        this.description = description;
        this.responsibilities = responsibilities;
        this.sharepointUrl = sharepointUrl;
        this.numberOfOpenPositions = numberOfOpenPositions;
        this.status = status;
    }
    public Date getClosingDate() {
        return closingDate;
    }
    public String getDescription() {
        return description;
    }
    public String getResponsibilities() {
        return responsibilities;
    }
    public String getSharepointUrl() {
        return sharepointUrl;
    }
    public int getNumberOfOpenPositions() {
        return numberOfOpenPositions;
    }
    public String getStatus() {
        return status;
    }
}
