INSERT INTO Band (bandName) VALUES('Principal');
INSERT INTO Band (bandName) VALUES('Manager');
INSERT INTO Band (bandName) VALUES('Consultant');
INSERT INTO Band (bandName) VALUES('Senior Associate');
INSERT INTO Band (bandName) VALUES('Trainee');
INSERT INTO Band (bandName) VALUES('Apprentice');

INSERT INTO Capability (capabilityName) VALUES ('Engineering');
INSERT INTO Capability (capabilityName) VALUES ('Data');
INSERT INTO Capability (capabilityName) VALUES ('People');
INSERT INTO Capability (capabilityName) VALUES ('AI');
INSERT INTO Capability (capabilityName) VALUES ('UX');
INSERT INTO Capability (capabilityName) VALUES ('Testing');


INSERT INTO `job-roles` (roleName,location,capabilityId,bandId,closingDate) VALUES("Software Engineer","Derry",'1','5','2024-12-25');
INSERT INTO `job-roles` (roleName,location,capabilityId,bandId,closingDate) VALUES("Tester","Derry",'6','2','2024-12-26');
