CREATE TABLE `Role` (
    RoleID TINYINT NOT NULL,
    Name varchar(64) NOT NULL,
    PRIMARY KEY (RoleID)
);

INSERT INTO Role(RoleID, Name) VALUES (1, 'Admin');
INSERT INTO Role(RoleID, Name) VALUES (2, 'User');

CREATE TABLE `User` (
    Username varchar(64) NOT NULL,
    Password varchar(200) NOT NULL,
    RoleID TINYINT NOT NULL,
    PRIMARY KEY (Username),
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
);

INSERT INTO User(Username, Password, RoleID)
    VALUES ('admin', '$2a$10$abwOc0Pn.kTEmWCa7GJ0ROXGwmwJXFzX6Fh.81fmS4zOZdjj81jzW', 1);
INSERT INTO User(Username, Password, RoleID)
    VALUES ('user', '$2a$10$DDH8AlCUekJsEspG9gTO4Od.D6UeqWAWkVS1mZJhNCN.1A90vQF2y', 2);