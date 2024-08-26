
CREATE TABLE `job-roles` (

    jobRoleId int PRIMARY KEY NOT NULL auto_increment,
    roleName varchar(75),
    location varchar(100),
    capabilityId int NOT NULL,
    bandId int NOT NULL,
    closingDate DATE,

    FOREIGN KEY(capabilityId) REFERENCES capability(capabilityId),
    FOREIGN KEY(bandId) REFERENCES band(bandId)

);


