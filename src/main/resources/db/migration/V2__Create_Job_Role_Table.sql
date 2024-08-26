
CREATE TABLE job-roles (

    jobRoleId int PRIMARY KEY NOT NULL,
    roleName varchar(75),
    location varchar(100),
    capabilityId int NOT NULL,
    bandId int NOT NULL,
    closingDate DATE,

    FOREIGN KEY(capabilityId) REFERENCES capability(capabilityId),
    FOREIGN KEY(bandId) REFERENCES band(nameId)

);


