INSERT INTO Band (bandName) VALUES('Grade 1 -£20,000 - 25,000');
INSERT INTO Band (bandName) VALUES('Grade 2 - £25,001 -  30,000');
INSERT INTO Band (bandName) VALUES('Grade 3 -£30,001 - 40,000');
INSERT INTO Band (bandName) VALUES('Grade 4- £40,001 - 50,000');
INSERT INTO Band (bandName) VALUES('Grade 5 -£50,001+');

INSERT INTO Capability (capabilityName) VALUES ('Intern');
INSERT INTO Capability (capabilityName) VALUES ('Graduate');
INSERT INTO Capability (capabilityName) VALUES ('Junior');
INSERT INTO Capability (capabilityName) VALUES ('Mid-Level');
INSERT INTO Capability (capabilityName) VALUES ('Senior');
INSERT INTO Capability (capabilityName) VALUES ('Lead');


INSERT INTO `job-roles` (roleName,location,capabilityId,bandId,closingDate) VALUES("Software Engineer","Derry",'1','1','2024-12-25');
INSERT INTO `job-roles` (roleName,location,capabilityId,bandId,closingDate) VALUES("Tester","Derry",'2','2','2024-12-26');
