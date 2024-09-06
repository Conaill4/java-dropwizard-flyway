CREATE TABLE `Role` (
    RoleID TINYINT NOT NULL,
    Name varchar(64) NOT NULL,
    PRIMARY KEY (RoleID)
);

INSERT INTO Role(RoleID, Name) VALUES (1, 'Admin');
INSERT INTO Role(RoleID, Name) VALUES (2, 'User');

CREATE TABLE `User` (
    Email varchar(64) NOT NULL,
    Password varchar(200) NOT NULL,
    RoleID TINYINT NOT NULL,
    PRIMARY KEY (Email),
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
);

INSERT INTO User(Email, Password, RoleID)
    VALUES ('admin@kainos.com', '$2a$10$hK06hO1rep2GeyyksoEA1ue01/ypRDpaYneXFTtaHtdT3DAFX1ayC', 1);
INSERT INTO User(Email, Password, RoleID)
    VALUES ('user@kainos.com', '$2a$10$y1uLZnPS7P2MH8MfAaTjI.Az9UcK5S.u5gtSacwuS5ALld7ia.fIq', 2);