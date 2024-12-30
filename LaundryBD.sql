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

CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    telefono VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    activo BOOLEAN NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    active BOOLEAN NOT NULL
);

CREATE TABLE service_laundry (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    description VARCHAR(255),
    active BOOLEAN NOT NULL,
    duration INT NOT NULL,
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

SELECT * FROM persistent_logins;
DROP TABLE employee;
DROP TABLE role;
DROP TABLE cliente;
DROP TABLE category;
DROP TABLE service_laundry;
SELECT * FROM employee;
SELECT * FROM role;
SELECT * FROM cliente;
SELECT * FROM category;
SELECT * FROM service_laundry;

INSERT INTO role (name) VALUES ('admin');
INSERT INTO role (name) VALUES ('employee');






