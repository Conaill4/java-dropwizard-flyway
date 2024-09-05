ALTER TABLE `job-roles`
ADD description varchar(500),
ADD responsibilities varchar(100),
ADD sharepointUrl varchar(150),
ADD numberOfOpenPositions smallint,
ADD statusId int;

ALTER TABLE `job-roles`
ADD CONSTRAINT FK_JobRoleStatus
FOREIGN KEY(statusId) REFERENCES Status(statusId);

UPDATE `job-roles`
SET description = "Kainos Software Engineer role in Derry",
    responsibilities = "You will be responsible for managing and updating software for clients all over the world",
    sharepointUrl = "https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration",
    statusId = 1,
    numberOfOpenPositions = 3
WHERE jobRoleId = 1;

UPDATE `job-roles`
SET description = "Kainos Test Engineer role in Derry",
    responsibilities = "You will be responsible for testing software for clients all over the world",
    sharepointUrl = "https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration",
    statusId = 1,
    numberOfOpenPositions = 2
WHERE jobRoleId = 2;

UPDATE `job-roles`
SET description = "Kainos Test Engineer role in Derry",
    responsibilities = "You will be responsible for testing software for clients all over the world",
    sharepointUrl = "https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration",
    statusId = 2,
    numberOfOpenPositions = 1
WHERE jobRoleId = 3;