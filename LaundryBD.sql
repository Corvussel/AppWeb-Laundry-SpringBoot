-- Active: 1735882053875@@b0vg062se0kqukm3t4yx-mysql.services.clever-cloud.com@3306
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
    status BOOLEAN DEFAULT true,
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

CREATE TABLE boleta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_boleta VARCHAR(255) NOT NULL,
    fecha_generada TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE payment_method (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    active BOOLEAN DEFAULT true
);

CREATE TABLE order_service (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT,
    client_id BIGINT,
    boleta_id BIGINT,
    total_servicio DOUBLE NOT NULL,
    descuento DOUBLE,
    precio_total DOUBLE NOT NULL,
    payment_method_id BIGINT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    observacion VARCHAR(255),
    tipo_servicio VARCHAR(255), 
    status VARCHAR(255),
    total_Cobro DOUBLE NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(id),
    FOREIGN KEY (client_id) REFERENCES cliente(id),
    FOREIGN KEY (boleta_id) REFERENCES boleta(id),
    FOREIGN KEY (payment_method_id) REFERENCES payment_method(id)
);
 
CREATE TABLE order_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_service_id BIGINT,
    nombre VARCHAR(255) NOT NULL,
    precio_unidad DOUBLE NOT NULL,
    cantidad INT NOT NULL,
    sub_total DOUBLE NOT NULL,
    detalle VARCHAR(255),
    FOREIGN KEY (order_service_id) REFERENCES order_service(id)
);
 
 -- ELIMINAR TABLAS
DROP TABLE employee;
DROP TABLE role;
DROP TABLE cliente;
DROP TABLE category;
DROP TABLE boleta;
DROP TABLE order_service;
DROP TABLE order_details; 
DROP TABLE service_laundry;
DROP TABLE payment_method; 

-- SELECCIONAR TABLAS
SELECT * FROM persistent_logins;
SELECT * FROM employee;
SELECT * FROM role;
SELECT * FROM cliente;
SELECT * FROM category;
SELECT * FROM service_laundry;
SELECT * FROM payment_method;
SELECT * FROM boleta;
SELECT * FROM order_service;
SELECT * FROM order_details;

INSERT INTO role (name) VALUES ('admin');
INSERT INTO role (name) VALUES ('employee');






