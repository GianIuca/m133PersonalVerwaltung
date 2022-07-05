DROP DATABASE IF EXISTS persons;
CREATE DATABASE persons;
USE persons;

DROP TABLE IF EXISTS person;

CREATE TABLE `persons`.`person`
(
    `id`        int          NOT NULL,
    `firstname` varchar(255) NOT NULL,
    `lastname`  varchar(255) NOT NULL,
    `email`     varchar(255) NOT NULL,
    `gender`    char(1)      NOT NULL,
    `birthdate` DATE         NOT NULL
);



ALTER TABLE `persons`.`person`
    ADD PRIMARY KEY (`id`);


ALTER TABLE `persons`.`person`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;



INSERT INTO `persons`.`person`(firstname, lastname, email, gender, birthdate)
VALUES ('Gianluca', 'Ferrara', 'gian@gmail.com', 'm',
        '2011-11-11');

INSERT INTO `persons`.`person`(firstname, lastname, email, gender, birthdate)
VALUES ('Mattia', 'Loiarro', 'mattia@gmail.com', 'm',
        '2011-11-11');
/*
CREATE USER 'user2'@'localhost' IDENTIFIED BY 'password2';
flush privileges;

SELECT CURRENT_USER;

select * from mysql.user;

GRANT ALL PRIVILEGES ON persons . * TO 'user2'@'localhost';
*/

