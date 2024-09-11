ALTER TABLE `job-roles`
ADD description varchar(500),
ADD responsibilities varchar(250),
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

INSERT INTO `job-roles`(jobRoleId, roleName, location, capabilityId, bandId, closingDate, description, responsibilities, sharepointUrl, numberOfOpenPositions, statusId)
VALUES
(4, 'Software Engineer', 'New York', 1, 3, '2024-01-15', 'Develop and maintain software applications.', 'Participate in daily stand-up meetings; Write clean, scalable code; Conduct code reviews; Collaborate with cross-functional teams.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 5, 1),
(5, 'Data Analyst', 'London', 2, 2, '2024-02-20', 'Analyze and interpret complex data sets.', 'Gather data from multiple sources; Create dashboards and reports; Work with stakeholders to understand data needs; Conduct statistical analysis.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 3, 1),
(6, 'DevOps Engineer', 'San Francisco', 3, 3, '2024-03-25', 'Maintain and improve CI/CD pipelines.', 'Automate deployment processes; Monitor system performance; Collaborate with developers for infrastructure needs; Ensure system reliability and scalability.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 4, 1),
(7, 'UX Designer', 'Berlin', 4, 2, '2024-04-30', 'Design user-friendly interfaces.', 'Create wireframes and prototypes; Conduct user research and testing; Collaborate with product managers and developers; Maintain design systems and guidelines.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 2, 1),
(8, 'Product Manager', 'Tokyo', 5, 4, '2024-05-10', 'Oversee product development lifecycle.', 'Define product strategy and roadmap; Collaborate with engineering, marketing, and sales; Manage product launches and releases; Analyze product performance and gather user feedback.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 1, 1),
(9, 'Network Administrator', 'Sydney', 6, 2, '2024-06-15', 'Manage and maintain network infrastructure.', 'Configure and maintain network equipment; Monitor network performance and security; Troubleshoot network issues; Implement network upgrades.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 3, 1),
(10, 'Database Administrator', 'New York', 1, 3, '2024-07-20', 'Maintain and optimize database systems.', 'Install and configure database servers; Monitor database performance; Develop and implement backup and recovery strategies; Optimize database queries.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 2, 1),
(11, 'Cybersecurity Analyst', 'London', 2, 2, '2024-08-25', 'Protect company assets from cyber threats.', 'Monitor security alerts and incidents; Conduct vulnerability assessments; Implement security controls; Develop security policies and procedures.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 3, 1),
(12, 'AI Researcher', 'San Francisco', 3, 3, '2024-09-30', 'Develop AI models and algorithms.', 'Conduct research in AI and machine learning; Design and implement AI solutions; Collaborate with cross-functional teams to integrate AI into products; Publish research papers and present at conferences.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 1, 1),
(13, 'QA Engineer', 'Berlin', 4, 2, '2024-10-15', 'Ensure software quality and reliability.', 'Develop and execute test cases; Identify and report bugs; Collaborate with developers to resolve issues; Conduct regression and performance testing.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 4, 1),
(14, 'Backend Developer', 'Tokyo', 5, 3, '2024-11-20', 'Build and maintain server-side applications.', 'Write server-side code; Design and implement APIs; Optimize server performance; Work with front-end developers for seamless integration.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 3, 1),
(15, 'Full Stack Developer', 'Sydney', 6, 4, '2024-12-05', 'Develop front-end and back-end components.', 'Design and develop web applications; Collaborate with UI/UX designers; Maintain and improve code quality; Troubleshoot and debug issues.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 5, 1),
(16, 'Mobile Developer', 'New York', 1, 3, '2024-12-15', 'Develop mobile applications for iOS and Android.', 'Write clean, maintainable code for mobile platforms; Collaborate with design and product teams; Conduct performance optimization; Stay updated with the latest mobile technologies.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 2, 1),
(17, 'Technical Writer', 'London', 2, 2, '2024-12-25', 'Create technical documentation for software products.', 'Write user manuals and guides; Collaborate with developers and QA teams; Ensure documentation is up-to-date; Gather feedback from users and stakeholders.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 1, 1),
(18, 'System Architect', 'San Francisco', 3, 5, '2024-12-30', 'Design and oversee complex software systems.', 'Define architecture standards and best practices; Work with stakeholders to align architecture with business goals; Perform system analysis and optimization; Lead architecture reviews.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 1, 1),
(19, 'Frontend Developer', 'Berlin', 4, 3, '2024-11-05', 'Develop client-side applications using modern web technologies.', 'Build responsive and user-friendly web interfaces; Work closely with designers; Optimize web applications for maximum speed; Stay up-to-date with emerging technologies.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 3, 1),
(20, 'Business Analyst', 'Tokyo', 5, 4, '2024-10-01', 'Analyze business requirements and processes.', 'Gather and document business requirements; Work with stakeholders to improve processes; Conduct data analysis to support decision-making; Develop process models and diagrams.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 2, 1),
(21, 'Cloud Engineer', 'Sydney', 6, 3, '2024-09-15', 'Manage cloud infrastructure and services.', 'Design and implement cloud solutions; Monitor cloud systems for security and performance; Automate cloud operations; Collaborate with development teams on cloud adoption.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 4, 1),
(22, 'Scrum Master', 'New York', 1, 2, '2024-08-10', 'Facilitate Agile scrum ceremonies and practices.', 'Lead daily stand-ups, sprint planning, and retrospectives; Coach teams in Agile practices; Resolve team impediments; Foster a collaborative and high-performing team environment.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 2, 1),
(23, 'IT Support Specialist', 'London', 2, 1, '2024-07-01', 'Provide technical support to end-users.', 'Respond to and resolve IT support requests; Install and configure hardware and software; Maintain IT documentation; Assist with network and server management.', 'https://www.microsoft.com/en-gb/microsoft-365/sharepoint/collaboration', 5, 1);
