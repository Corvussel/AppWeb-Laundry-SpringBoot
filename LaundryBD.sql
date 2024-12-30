-- Active: 1735511517208@@b0vg062se0kqukm3t4yx-mysql.services.clever-cloud.com@3306@b0vg062se0kqukm3t4yx
CREATE DATABASE sistema_ventas;

USE sistema_ventas;

CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) PRIMARY KEY,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL
);

CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    role_id BIGINT,
    status VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    profile_image VARCHAR(255),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

SELECT * FROM persistent_logins;
DROP TABLE employee;
DROP TABLE role;
SELECT * FROM employee;
SELECT * FROM role;

INSERT INTO role (name) VALUES ('admin');
INSERT INTO role (name) VALUES ('employee');

 




