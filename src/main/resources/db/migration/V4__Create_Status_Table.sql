CREATE TABLE `Status`(
    statusId int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    statusName char(6)
);

INSERT INTO `Status`(statusId, statusName)
VALUES (1, "OPEN"), (2, "CLOSED");