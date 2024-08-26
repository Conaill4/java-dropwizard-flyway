CREATE TABLE capability(
capabilityId int PRIMARY KEY AUTO_INCREMENT NOT NULL,
capabilityName enum('Intern', 'Graduate','Junior', 'Mid-Level', 'Senior', 'Lead')
);

CREATE TABLE band (
bandId int PRIMARY KEY AUTO_INCREMENT NOT NULL,
bandName enum('Grade 1 -£20,000 - 25,000','Grade 2 - £25,001 -  30,000','Grade 3 -£30,001 - 40,000','Grade 4- £40,001 - 50,000','Grade 5 -£50,001+')
);
