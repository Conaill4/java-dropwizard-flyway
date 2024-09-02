CREATE TABLE Capability(
capabilityId int PRIMARY KEY AUTO_INCREMENT NOT NULL,
capabilityName enum("Engineering", "Data", "People", "AI", "UX", "Testing")
);

CREATE TABLE Band (
bandId int PRIMARY KEY AUTO_INCREMENT NOT NULL,
bandName enum("Principal", "Manager", "Consultant", "Senior Associate", "Trainee", "Apprentice")
);
